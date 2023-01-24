package com.usv.booking.features.utils;

import com.usv.booking.features.user.Account;
import com.usv.booking.features.user.AccountDto;
import com.usv.booking.features.user.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CacheService {

  private final AccountService accountService;

  @Cacheable("users")
  public List<AccountDto> getUsers() {
    return accountService.getAllUsers();
  }

  @CacheEvict(value = "users", allEntries = true)
  public void deleteCache() {
  }

}