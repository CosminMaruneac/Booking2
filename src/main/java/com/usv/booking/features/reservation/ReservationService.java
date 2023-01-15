package com.usv.booking.features.reservation;

import com.usv.booking.features.room.Room;
import com.usv.booking.features.room.RoomRepository;
import com.usv.booking.features.room.RoomReservationDto;
import com.usv.booking.features.user.Account;
import com.usv.booking.features.user.AccountRepository;
import com.usv.booking.features.utils.BadRequestException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.time.LocalDate;
import java.time.Period;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationService {

  private final ReservationRepository reservationRepository;
  private final ModelMapper modelMapper;
  private final AccountRepository accountRepository;
  private final RoomRepository roomRepository;

  @Autowired
  public ReservationService(ReservationRepository reservationRepository,
                            ModelMapper modelMapper,
                            AccountRepository accountRepository,
                            RoomRepository roomRepository) {
    this.reservationRepository = reservationRepository;
    this.modelMapper = modelMapper;
    this.accountRepository = accountRepository;
    this.roomRepository = roomRepository;
    this.modelMapper.addMappings(Utils.reservationDtoPropertyMap);
  }

  public ReservationDto create(ReservationDto dto) {

    Reservation reservation = modelMapper.map(dto, Reservation.class);

    Account owner = accountRepository.findById(dto.getOwnerId()).orElseThrow(EntityExistsException::new);
    reservation.setOwner(owner);
    reservation.setReservationStatus(ReservationStatus.NEW);

    List<Room> rooms = roomRepository.findAllById(dto.getRooms().stream()
        .map(RoomReservationDto::getId)
        .collect(Collectors.toList()));

    checkRoomsAvailability(rooms, dto.getDateFrom(), dto.getDateTo());
    reservation.setPrice(calculateRoomsPrice(rooms, Period.between(dto.getDateFrom(), dto.getDateTo()).getDays()));

    reservation.setRooms(new HashSet<>(rooms));

    reservationRepository.save(reservation);

    return modelMapper.map(reservation, ReservationDto.class);
  }

  private Double calculateRoomsPrice(List<Room> rooms, int days) {

    return rooms.stream()
        .mapToDouble(room -> room.getPricePerNight() * days)
        .sum();
  }


  private void checkRoomsAvailability(List<Room> rooms, LocalDate dateFrom, LocalDate dateTo) {

    List<Reservation> existingReservations = reservationRepository.findExistingReservationsBetweenDates(dateFrom, dateTo);

    List<Long> reservedRoomsIds = existingReservations.stream()
        .flatMap(reservation -> reservation.getRooms().stream())
        .map(Room::getId)
        .distinct()
        .collect(Collectors.toList());

    rooms.forEach(room -> {
      if (reservedRoomsIds.contains(room.getId())) {
        throw new BadRequestException("Room with id " + room.getId() + " is already reserved between these dates!");
      }
    });
  }

  public ReservationDto getById(Long id) {

    return reservationRepository.findById(id)
        .map(reservation -> modelMapper.map(reservation, ReservationDto.class))
        .orElseThrow(EntityExistsException::new);
  }

  public ReservationDto update(ReservationDto dto) {

    return reservationRepository.findById(dto.getId())
        .map(reservation -> {
          reservation.setDateFrom(dto.getDateFrom());
          reservation.setDateTo(dto.getDateTo());

          reservationRepository.save(reservation);

          return modelMapper.map(reservation, ReservationDto.class);
        })
        .orElseThrow(EntityExistsException::new);
  }

  public ReservationDto cancelReservation(Long id) {

    return reservationRepository.findById(id)
        .map(reservation -> {
          if (LocalDate.now().isAfter(reservation.getDateFrom()))
            throw new BadRequestException("You cannot cancel this reservation!");

          reservation.setReservationStatus(ReservationStatus.CANCELED);

          reservationRepository.save(reservation);
          return modelMapper.map(reservation, ReservationDto.class);
        })
        .orElseThrow(EntityExistsException::new);

  }
}
