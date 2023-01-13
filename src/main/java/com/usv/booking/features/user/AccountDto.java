package com.usv.booking.features.user;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class AccountDto implements Serializable {

  private Integer id;

  private String password;

  private String name;

  private String email;

  private String phone;

  private LocalDate birthday;
}
