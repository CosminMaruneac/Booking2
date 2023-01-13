package com.usv.booking.features.user;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountService {

  private final AccountRepository accountRepository;
  private final ModelMapper modelMapper;

  @Autowired
  public AccountService(AccountRepository accountRepository, ModelMapper modelMapper) {
    this.accountRepository = accountRepository;
    this.modelMapper = modelMapper;
  }

  public List<AccountDto> getAll() {

    return accountRepository.findAll().stream()
        .map(user -> modelMapper.map(user, AccountDto.class))
        .collect(Collectors.toList());
  }

  public void softDelete(Integer id) {

    Optional<Account> account = accountRepository.findById(id);

    if (account.isPresent()) {
      account.get().setIsDeleted(true);
      accountRepository.save(account.get());
    }
  }
}
