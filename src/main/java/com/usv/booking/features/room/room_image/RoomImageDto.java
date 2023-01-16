package com.usv.booking.features.room.room_image;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomImageDto implements Serializable {

  private Long id;

  private String url;

  private Long roomId;
}
