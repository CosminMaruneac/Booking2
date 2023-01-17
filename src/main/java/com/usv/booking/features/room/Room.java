package com.usv.booking.features.room;

import com.usv.booking.features.facility.Facility;
import com.usv.booking.features.reservation.Reservation;
import com.usv.booking.features.room.room_image.RoomImage;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Room implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
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

  @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.LAZY)
  @JoinTable(
      name = "room_facility",
      joinColumns = @JoinColumn(name = "room_id"),
      inverseJoinColumns = @JoinColumn(name = "facility_id"))
  private Set<Facility> facilities = new HashSet<>();

  @ManyToMany(mappedBy = "rooms", cascade = CascadeType.REMOVE)
  private Set<Reservation> reservations = new HashSet<>();

  @OneToMany(mappedBy = "room", fetch = FetchType.LAZY,
      cascade = CascadeType.REMOVE, orphanRemoval = true)
  private Set<RoomImage> images = new HashSet<>();
}
