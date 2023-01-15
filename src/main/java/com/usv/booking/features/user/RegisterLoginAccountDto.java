package com.usv.booking.features.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Access;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterLoginAccountDto {

  private String email;

  private String password;

  @Enumerated(EnumType.STRING)
  @JsonIgnore
  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  private AccountType accountType = AccountType.USER;

}
