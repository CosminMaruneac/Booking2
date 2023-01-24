package com.usv.booking.features.user;

import com.usv.booking.features.utils.BadRequestException;
import org.mindrot.jbcrypt.BCrypt;
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

  public List<AccountDto> getAllUsers() {

    return getAll();
  }

  public void softDelete(Long id) {

    Optional<Account> account = accountRepository.findById(id);

    if (account.isPresent()) {
      account.get().setIsDeleted(true);
      accountRepository.save(account.get());
    }
  }

  public RegisterResponse register(RegisterLoginAccountDto registerLoginAccountDto) {

    if (Boolean.TRUE.equals(accountRepository.existsByEmail(registerLoginAccountDto.getEmail()))) {
      return RegisterResponse.builder()
          .message("This email address already exists!")
          .statusCode(400)
          .build();
    }

    Account account = new Account();

    account.setEmail(registerLoginAccountDto.getEmail());
    account.setPassword(encodePassword(registerLoginAccountDto.getPassword()));
    account.setAccountType(registerLoginAccountDto.getAccountType());

    accountRepository.save(account);

    return RegisterResponse.builder()
        .statusCode(200)
        .message("Account successfully created!")
        .build();
  }

  private String encodePassword(String password) {

    return BCrypt.hashpw(password, BCrypt.gensalt());

  }

  private Boolean verifyPassword(String password, String enteredPassword) {

    return BCrypt.checkpw(enteredPassword, password);

  }

  public AccountDto login(RegisterLoginAccountDto registerLoginAccountDto) {

    if (Boolean.FALSE.equals(accountRepository.existsByEmail(registerLoginAccountDto.getEmail()))) {
      throw new BadRequestException("Email or password are incorrect");
    }

    Account account = accountRepository.getAccountByEmail(registerLoginAccountDto.getEmail());

    if (Boolean.FALSE.equals(verifyPassword(account.getPassword(), registerLoginAccountDto.getPassword()))) {
      throw new BadRequestException("Email or password are incorrect");
    }

    return modelMapper.map(account, AccountDto.class);
  }
}
