package com.usv.booking.features.room;

import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/rooms")
@AllArgsConstructor
public class RoomController {

  private final RoomService roomService;

  @PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public RoomDto create(@RequestBody RoomDto dto) {

    return roomService.create(dto);
  }

  @GetMapping(path = "/{id}")
  public RoomDto getById(@PathVariable(name = "id") Long id) {

    return roomService.getById(id);
  }

  @GetMapping(path = "/view-list")
  public List<RoomListViewDto> viewRoomList(@RequestParam(name = "type") Optional<RoomType> roomType,
                                            @RequestParam(name = "price") Optional<Double> price,
                                            @RequestParam(name = "dateFrom")  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate dateFrom,
                                            @RequestParam(name = "dateTo") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)  LocalDate dateTo) {

    return roomService.viewRoomList(roomType.orElse(null), price.orElse(null), dateFrom, dateTo);
  }

  @PutMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public RoomDto update(@RequestBody RoomDto dto) {

    return roomService.update(dto);
  }

  @DeleteMapping(path = "/{id}")
  public void deleteById(@PathVariable(name = "id") Long id) {

    roomService.deleteById(id);
  }
}
