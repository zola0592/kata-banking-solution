package org.kata.banking.usecase;

import org.kata.banking.FactoryAccount;
import org.kata.banking.Repository;
import org.kata.banking.UseCase;
import org.kata.banking.command.MakeDepositCommand;
import org.kata.banking.domain.Account;
import org.kata.banking.domain.Transaction;

/**
 * <p>Cas d'utilisation pour effectuer un dépôt dans un {@link Account}</p>
 *
 * @author Ahmed Ali DAO 2021-10-29
 */
public class MakeDeposit implements UseCase<Transaction, MakeDepositCommand> {

    private final FactoryAccount factoryAccount;

    public MakeDeposit(Repository repository) {
        this.factoryAccount = new FactoryAccount(repository);
    }

    @Override
    public Transaction perform(MakeDepositCommand command) {
        var accountReference = command.accountReference();
        var account = factoryAccount.searchAccount(accountReference);
        var transactionAmount = command.transactionAmount();
        return account.deposit(transactionAmount);
    }

}
