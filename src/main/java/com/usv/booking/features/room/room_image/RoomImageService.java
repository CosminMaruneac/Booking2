package com.usv.booking.features.room.room_image;

import com.usv.booking.features.room.Room;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomImageService {

  private final ModelMapper modelMapper;
  private final RoomImageRepository roomImageRepository;

  @Autowired
  public RoomImageService(ModelMapper modelMapper, RoomImageRepository roomImageRepository) {
    this.modelMapper = modelMapper;
    this.roomImageRepository = roomImageRepository;
  }


  public RoomImageDto create(RoomImageDto roomImageDto, Room room) {

    RoomImage roomImage = modelMapper.map(roomImageDto, RoomImage.class);
    roomImage.setRoom(room);

    roomImageRepository.save(roomImage);

    return modelMapper.map(roomImage, RoomImageDto.class);
  }
}
