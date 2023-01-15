package com.usv.booking.features.user;

import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountDto implements Serializable {

  private Long id;

  private String password;

  private String firstName;

  private String lastName;

  private String email;

  private String phoneNumber;

  @Enumerated(EnumType.STRING)
  private AccountType accountType;

  private String billingAddress;

  private String profileImageLink;

  private LocalDate birthDate;

  private Boolean isDeleted;
}
