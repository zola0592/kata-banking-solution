package org.kata.banking;

import org.kata.banking.domain.Account;

import java.util.Optional;

public interface Repository {
  Optional<Account> findAccountByReference(String reference);
}
