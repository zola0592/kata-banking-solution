package org.kata.banking.usecase;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kata.banking.MemoryRepository;
import org.kata.banking.Repository;
import org.kata.banking.UseCase;
import org.kata.banking.command.MakeDepositCommand;
import org.kata.banking.command.MakeWithdrawalCommand;
import org.kata.banking.domain.Account;
import org.kata.banking.domain.Transaction;

import java.util.Optional;

/**
 * <p>Test unitaire du cas d'utilisation {@link RetrieveStatementAccount}.</p>
 *
 * @author Ahmed Ali DAO 2021-10-29
 */
class RetrieveStatementAccountTest {

    Repository repository;
    UseCase<String, String> retrieveStatementAccountUseCase;
    UseCase<Transaction, MakeWithdrawalCommand> makeWithdrawalUseCase;
    UseCase<Transaction, MakeDepositCommand> makeDepositUseCase;
    double initialBalance = 500.0;

    @BeforeEach
    void setUp() {
        this.repository = new MemoryRepository(initialBalance);
        this.retrieveStatementAccountUseCase = new RetrieveStatementAccount(this.repository);
        this.makeWithdrawalUseCase = new MakeWithdrawal(this.repository);
        this.makeDepositUseCase = new MakeDeposit(this.repository);
    }

    @Test
    @DisplayName("Test l'affichage du relevé bancaire d'un compte")
    void perform() {
        // Given
        var reference = "5";

        // Effectuer une operation de retrait
        final var amountToWithdraw = 500.0;
        var commandWithdraw = new MakeWithdrawalCommand(reference, amountToWithdraw);
        var withDrawTransaction = this.makeWithdrawalUseCase.perform(commandWithdraw);

        // Effectuer une operation de depot
        final var amountToDeposit = 1000.0;
        var commandDeposit = new MakeDepositCommand(reference, amountToDeposit);
        var depositTransaction = this.makeDepositUseCase.perform(commandDeposit);

        // When
        var printStatement = this.retrieveStatementAccountUseCase.perform(reference);

        // Then
        Optional<Account> accountOptional = this.repository.findAccountByReference(reference);
        Assertions.assertTrue(accountOptional.isPresent());
        var account = accountOptional.get();
        var currentBalance = account.balance();

        Assertions.assertNotNull(printStatement);
        Assertions.assertTrue(printStatement.contains(withDrawTransaction.print()));
        Assertions.assertTrue(printStatement.contains(depositTransaction.print()));
        Assertions.assertTrue(printStatement.contains("Solde du compte : " + currentBalance.moneyRepresentation()));
    }
}
