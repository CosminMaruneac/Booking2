package com.usv.booking.features.reservation;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/reservations")
@AllArgsConstructor
public class ReservationController {

  private final ReservationService reservationService;

  @PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ReservationDto create(@RequestBody ReservationDto dto) {

    return reservationService.create(dto);
  }

  @PutMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ReservationDto update(@RequestBody ReservationDto dto) {

    return reservationService.update(dto);
  }

  @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ReservationDto getById(@PathVariable(name = "id") Long id) {

    return reservationService.getById(id);
  }

  @PatchMapping(path = "/{id}/cancel", produces = MediaType.APPLICATION_JSON_VALUE)
  public ReservationDto cancelReservation(@PathVariable(name = "id") Long id) {

    return reservationService.cancelReservation(id);
  }

  @GetMapping(path = "/owner/{ownerId}", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<ReservationDto> getReservationsByOwnerId(@PathVariable (name = "ownerId") Long ownerId){

    return reservationService.getByOwnerId(ownerId);
  }
}
