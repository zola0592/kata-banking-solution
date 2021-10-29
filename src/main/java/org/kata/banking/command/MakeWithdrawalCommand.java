package org.kata.banking.command;

/**
 * <p>Commande pour effectuer un retrait dans un compte.</p>
 *
 * @author Ahmed Ali DAO 2021-10-29
 */
public class MakeWithdrawalCommand extends MakeDepositCommand {

  public MakeWithdrawalCommand(String accountReference, double amount) {
    super(accountReference, amount);
  }
}
