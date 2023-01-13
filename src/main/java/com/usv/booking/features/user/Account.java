package com.usv.booking.features.user;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "account")
@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Account implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
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
