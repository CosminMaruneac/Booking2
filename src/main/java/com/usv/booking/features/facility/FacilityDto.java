package com.usv.booking.features.facility;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class FacilityDto implements Serializable {

  private Long id;

  private String name;
}
