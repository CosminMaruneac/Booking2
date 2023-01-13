package com.usv.booking.features.room;

import com.usv.booking.features.facility.Facility;
import com.usv.booking.features.facility.FacilityDto;
import com.usv.booking.features.reservation.Reservation;
import com.usv.booking.features.reservation.ReservationDto;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
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

  private String imageUrl;

  private String description;

  @Enumerated(EnumType.STRING)
  private CleanStatus cleanStatus;

  private Set<FacilityDto> facilities = new HashSet<>();

  private Set<ReservationDto> reservations = new HashSet<>();

}