package com.usv.booking.features.reservation;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.usv.booking.features.room.RoomDto;
import com.usv.booking.features.room.RoomReservationDto;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ReservationDto implements Serializable {

  private Long id;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private LocalDate dateFrom;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private LocalDate dateTo;

  @Enumerated(EnumType.STRING)
  private ReservationStatus reservationStatus;

  private Double price;

  private String ownerName;

  private Long ownerId;

  private Set<RoomReservationDto> rooms = new HashSet<>();
}
