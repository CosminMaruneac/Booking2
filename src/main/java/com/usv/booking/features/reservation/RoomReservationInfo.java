package com.usv.booking.features.reservation;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class RoomReservationInfo {

  private Long id;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
  private LocalDate dateFrom;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
  private LocalDate dateTo;

  @Enumerated(EnumType.STRING)
  private ReservationStatus reservationStatus;

  private Double price;

  private String ownerName;

  private Long ownerId;

}
