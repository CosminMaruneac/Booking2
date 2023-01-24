package com.usv.booking.features.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {

  Boolean existsByEmail(String email);

  Account getAccountByEmail(String email);

  List<Account> findAllByIsDeletedFalse();
}
