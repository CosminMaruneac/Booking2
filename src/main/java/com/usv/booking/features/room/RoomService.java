package com.usv.booking.features.room;

import com.usv.booking.features.facility.Facility;
import com.usv.booking.features.facility.FacilityDto;
import com.usv.booking.features.facility.FacilityRepository;
import com.usv.booking.features.facility.FacilityService;
import com.usv.booking.features.reservation.Reservation;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RoomService {

  private final ModelMapper modelMapper;

  private final RoomRepository roomRepository;

  private final FacilityRepository facilityRepository;

  public RoomService(ModelMapper modelMapper,
                     RoomRepository roomRepository,
                     FacilityService facilityService,
                     FacilityRepository facilityRepository) {
    this.modelMapper = modelMapper;
    this.roomRepository = roomRepository;
    this.facilityRepository = facilityRepository;
  }

  public RoomDto create(RoomDto dto) {

    Room room = modelMapper.map(dto, Room.class);

    List<Facility> facilities = facilityRepository.getFacilitiesByIdIn(dto.getFacilities().stream()
        .map(FacilityDto::getId)
        .collect(Collectors.toList()));
    room.setFacilities(new HashSet<>(facilities));

    return modelMapper.map(roomRepository.save(room), RoomDto.class);
  }

  public RoomDto getById(Long id) {

    return roomRepository.findById(id)
        .map(room -> modelMapper.map(room, RoomDto.class))
        .orElseThrow();
  }

  public List<RoomListViewDto> viewRoomList(RoomType roomType, Double price, LocalDate dateFrom, LocalDate dateTo) {

    return roomRepository.findAll().stream()
        .filter(room -> Objects.isNull(roomType) ? true : room.getRoomType().equals(roomType))
        .filter(room -> Objects.isNull(price) ? true : room.getPricePerNight().equals(price))
        .filter(room -> {
          if (Objects.isNull(dateFrom) || Objects.isNull(dateTo)) {
            return true;
          }

          List<Reservation> reservations = new ArrayList<>(room.getReservations());
          for (Reservation r : reservations) {
            if (r.getDateFrom().isAfter(dateFrom) && r.getDateFrom().isBefore(dateTo))
              return true;
            if (r.getDateTo().isAfter(dateFrom) && r.getDateTo().isBefore(dateTo))
              return true;
          }
          return false;
        })
        .map(room -> modelMapper.map(room, RoomListViewDto.class))
        .collect(Collectors.toList());
  }

  public RoomDto update(RoomDto dto) {

    return roomRepository.findById(dto.getId())
        .map(room -> {
          room.setRoomNumber(dto.getRoomNumber());
          room.setRoomType(dto.getRoomType());
          room.setCapacity(dto.getCapacity());
          room.setCleanStatus(dto.getCleanStatus());
          room.setDescription(dto.getDescription());
          room.setImageUrl(dto.getImageUrl());
          room.setPetFriendly(dto.getPetFriendly());
          room.setPricePerNight(dto.getPricePerNight());
          room.setBedNumber(dto.getBedNumber());
          room.setTitle(dto.getTitle());

          roomRepository.save(room);
          return modelMapper.map(room, RoomDto.class);
        })
        .orElseThrow(EntityExistsException::new);
  }
}
