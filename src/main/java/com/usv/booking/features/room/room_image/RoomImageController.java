package com.usv.booking.features.room.room_image;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/room-images")
@AllArgsConstructor
public class RoomImageController {

  private final RoomImageService roomImageService;
}
