package com.usv.booking.features.room;

import com.usv.booking.features.facility.Facility;
import com.usv.booking.features.facility.FacilityDto;
import com.usv.booking.features.facility.FacilityRepository;
import com.usv.booking.features.facility.FacilityService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
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
}
