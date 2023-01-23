package com.usv.booking.features.room;

import com.usv.booking.features.room.room_image.RoomImageDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomListViewDto implements Serializable {

  private Long id;

  private String title;

  private Integer capacity;

  private Integer bedNumber;

  @Enumerated(EnumType.STRING)
  private RoomType roomType;

  private Double pricePerNight;

  private String imageUrl;

  private String description;

  private Set<RoomImageDto> images;
}
