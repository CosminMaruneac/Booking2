package com.usv.booking.features.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/accounts")
public class AccountController {

  private final AccountService accountService;

  @Autowired
  public AccountController(AccountService accountService) {
    this.accountService = accountService;
  }

  @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<AccountDto>> getAll() {

    return ResponseEntity.ok(accountService.getAll());
  }

  @DeleteMapping(path = "/{id}")
  public void delete(@PathVariable(name = "id") Long id) {

    accountService.softDelete(id);
  }

  @PostMapping(path = "/register")
  public RegisterResponse register(@RequestBody RegisterLoginAccountDto registerLoginAccountDto) {

    return accountService.register(registerLoginAccountDto);
  }

  @PostMapping(path = "/login")
  public AccountDto login(@RequestBody RegisterLoginAccountDto registerLoginAccountDto) {

    return accountService.login(registerLoginAccountDto);
  }
}
