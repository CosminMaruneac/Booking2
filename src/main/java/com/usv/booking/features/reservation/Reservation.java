package com.usv.booking.features.reservation;

import com.usv.booking.features.room.Room;
import com.usv.booking.features.user.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reservation implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private LocalDate dateFrom;

  private LocalDate dateTo;

  @Enumerated(EnumType.STRING)
  private ReservationStatus reservationStatus;

  private Double price;

  private String ownerName;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "owner_id")
  private Account owner;

  @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
  @JoinTable(
      name = "reservation_room",
      joinColumns = @JoinColumn(name = "reservation_id"),
      inverseJoinColumns = @JoinColumn(name = "room_id"))
  private Set<Room> rooms = new HashSet<>();

}
