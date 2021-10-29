package org.kata.banking.usecase;

import org.kata.banking.FactoryAccount;
import org.kata.banking.Repository;
import org.kata.banking.UseCase;
import org.kata.banking.command.MakeWithdrawalCommand;
import org.kata.banking.domain.Account;
import org.kata.banking.domain.Transaction;

/**
 * <p>Cas d'utilisation pour effectuer un retrait dans un {@link Account}</p>
 *
 * @author Ahmed Ali DAO 2021-10-29
 */
public class MakeWithdrawal implements UseCase<Transaction, MakeWithdrawalCommand> {

    private final FactoryAccount factoryAccount;

    public MakeWithdrawal(Repository repository) {
        this.factoryAccount = new FactoryAccount(repository);
    }

    @Override
    public Transaction perform(MakeWithdrawalCommand command) {
        var accountReference = command.accountReference();
        var account = factoryAccount.searchAccount(accountReference);
        var transactionAmount = command.transactionAmount();
        return account.withdraw(transactionAmount);
    }
}
