package com.usv.booking.features.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/api/v1/users")
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

  @GetMapping(path = "/index")
  public String getIndex() {
    return "index";
  }

  @DeleteMapping(path = "/{id}")
  public void delete(@PathVariable(name = "id") Integer id) {

     accountService.softDelete(id);
  }
}
