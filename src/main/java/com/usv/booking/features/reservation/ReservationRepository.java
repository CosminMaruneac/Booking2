package com.usv.booking.features.reservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

  @Query("SELECT r FROM Reservation r " +
      "WHERE (r.dateFrom >= :dateFrom AND r.dateFrom <= :dateTo) " +
      "OR (r.dateTo >= :dateFrom AND r.dateTo <= :dateTo)")
  List<Reservation> findExistingReservationsBetweenDates(LocalDate dateFrom, LocalDate dateTo);
}
