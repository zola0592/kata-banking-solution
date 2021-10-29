package org.kata.banking.usecase;

import org.kata.banking.FactoryAccount;
import org.kata.banking.Repository;
import org.kata.banking.UseCase;
import org.kata.banking.domain.Account;

/**
 * <p>Cas d'utilisation pour l'affichage sur la console le relevé d'un {@link Account}.</p>
 *
 * @author Ahmed Ali DAO 2021-10-29
 */
public class RetrieveStatementAccount implements UseCase<String, String> {

    private final FactoryAccount factoryAccount;

    public RetrieveStatementAccount(Repository repository) {
        this.factoryAccount = new FactoryAccount(repository);
    }

    @Override
    public String perform(String command) {
        var account = this.factoryAccount.searchAccount(command);
        return account.printStatement();
    }
}
