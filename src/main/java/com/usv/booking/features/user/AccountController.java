package com.usv.booking.features.user;

import com.usv.booking.features.utils.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/accounts")
public class AccountController {

  private final AccountService accountService;
  private final CacheService cacheService;

  @Autowired
  public AccountController(AccountService accountService, CacheService cacheService) {
    this.accountService = accountService;
    this.cacheService = cacheService;
  }

  @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<AccountDto>> getAll() {

    return ResponseEntity.ok(cacheService.getUsers());
  }

  @DeleteMapping(path = "/{id}")
  public void delete(@PathVariable(name = "id") Long id) {

    accountService.softDelete(id);
  }

  @PostMapping(path = "/register")
  public RegisterResponse register(@RequestBody RegisterLoginAccountDto registerLoginAccountDto) {

    RegisterResponse register = accountService.register(registerLoginAccountDto);
    cacheService.deleteCache();
    cacheService.getUsers();

    return register;
  }

  @PostMapping(path = "/login")
  public AccountDto login(@RequestBody RegisterLoginAccountDto registerLoginAccountDto) {

    return accountService.login(registerLoginAccountDto);
  }
}
