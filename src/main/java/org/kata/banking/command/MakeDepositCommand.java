package org.kata.banking.command;

import org.kata.banking.domain.Amount;
import org.kata.banking.domain.BankAmount;

/**
 * <p>Commande pour effectuer un dépôt dans un compte.</p>
 *
 * @author Ahmed Ali DAO 2021-10-29
 */
public class MakeDepositCommand {

  private final String accountReference;
  private final double amount;

  public MakeDepositCommand(String accountReference, double amount) {
    this.accountReference = accountReference;
    this.amount = amount;
  }

  public String accountReference() {
    return accountReference;
  }

  public Amount transactionAmount() {
    return new BankAmount(this.amount);
  }

}
