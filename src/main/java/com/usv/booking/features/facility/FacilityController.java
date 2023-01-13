package com.usv.booking.features.facility;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/facilities")
@AllArgsConstructor
public class FacilityController {

  public final FacilityService facilityService;

  @PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public List<FacilityDto> bulkCreate(@RequestBody List<FacilityDto> dtos) {

    return facilityService.bulkCreate(dtos);
  }

  @PutMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public FacilityDto update(@RequestBody FacilityDto dto) {

    return facilityService.update(dto);
  }

  @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<FacilityDto> getAll() {

    return facilityService.getAll();
  }

  @DeleteMapping(path = "")
  public void bulkDelete(@RequestParam(name = "ids") List<Long> ids) {

    facilityService.bulkDelete(ids);
  }
}
