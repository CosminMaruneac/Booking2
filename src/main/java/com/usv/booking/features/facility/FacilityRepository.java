package com.usv.booking.features.facility;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FacilityRepository extends JpaRepository<Facility, Long> {

  List<Facility> getFacilitiesByIdIn(List<Long> ids);

}