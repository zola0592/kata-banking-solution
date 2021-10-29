package org.kata.banking;

import org.kata.banking.domain.Account;
import org.kata.banking.exception.BankAccountNotFoundException;

public class FactoryAccount {

  private final Repository repository;

  public FactoryAccount(Repository repository) {
    this.repository = repository;
  }

  public Account searchAccount(String accountReference) {
    var optionalAccount = this.repository.findAccountByReference(accountReference);
    return optionalAccount
        .orElseThrow(() -> new BankAccountNotFoundException(
            "Le compte bancaire avec la reference " + accountReference + " n'existe pas !"));
  }
}
