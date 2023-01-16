package com.usv.booking.features.room;

import com.usv.booking.features.facility.FacilityDto;
import com.usv.booking.features.reservation.RoomReservationInfo;
import com.usv.booking.features.room.room_image.RoomImageDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomDto implements Serializable {

  private Long id;

  private String title;

  private Integer roomNumber;

  private Integer capacity;

  private Integer bedNumber;

  @Enumerated(EnumType.STRING)
  private RoomType roomType;

  private Boolean petFriendly;

  private Double pricePerNight;

  private String description;

  @Enumerated(EnumType.STRING)
  private CleanStatus cleanStatus;

  private Set<FacilityDto> facilities = new HashSet<>();

  private Set<RoomReservationInfo> reservations = new HashSet<>();

  private Set<RoomImageDto> images = new HashSet<>();
}