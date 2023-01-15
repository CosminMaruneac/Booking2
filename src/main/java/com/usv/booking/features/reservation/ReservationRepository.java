package com.usv.booking.features.reservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

  @Query("SELECT r FROM Reservation r " +
      "WHERE (r.dateFrom >= :dateFrom AND r.dateFrom <= :dateTo) " +
      "OR (r.dateTo >= :dateFrom AND r.dateTo <= :dateTo)")
  List<Reservation> findExistingReservations(LocalDate dateFrom, LocalDate dateTo);


}
