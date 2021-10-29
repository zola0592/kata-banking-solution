package org.kata.banking.usecase;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kata.banking.MemoryRepository;
import org.kata.banking.Repository;
import org.kata.banking.UseCase;
import org.kata.banking.command.MakeDepositCommand;
import org.kata.banking.domain.Account;
import org.kata.banking.domain.Transaction;
import org.kata.banking.exception.BankAccountNotFoundException;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * <p>Test unitaire du cas d'utilisation {@link MakeDeposit}.</p>
 *
 * @author Ahmed Ali DAO 2021-10-29
 */
class MakeDepositTest {

    UseCase<Transaction, MakeDepositCommand> makeDepositUseCase;
    Repository repository;
    double initialBalance = 500.0;

    @BeforeEach
    void setUp() {
        this.repository = new MemoryRepository(initialBalance);
        this.makeDepositUseCase = new MakeDeposit(this.repository);
    }

    @Test
    @DisplayName("Test le dépôt d'argent dans un compte récupérer dans une DB à partir de sa référence unique")
    void makeDeposit() {
        // Given
        final var accountReference = "1";
        final var amountToDeposit = 500.0;

        final var realBalance = BigDecimal.valueOf(amountToDeposit + this.initialBalance);
        var command = new MakeDepositCommand(accountReference, amountToDeposit);

        // When
        this.makeDepositUseCase.perform(command);

        // Then
        Optional<Account> accountOptional = this.repository.findAccountByReference(accountReference);
        Assertions.assertTrue(accountOptional.isPresent());
        final var account = accountOptional.get();
        final var currentBalance = account.balance();
        Assertions.assertEquals(realBalance, currentBalance.value());

    }

    @Test
    @DisplayName("Test l'impossibilité d'effectuer un dépôt d'argent sur un compte inexistant")
    void impossibleMakeDeposit() {
        // Given
        final String accountReference = "100";
        final var amountToDeposit = 500.0;

        var command = new MakeDepositCommand(accountReference, amountToDeposit);

        // When
        var bankAccountNotFoundException = Assertions
                .assertThrows(BankAccountNotFoundException.class, () -> this.makeDepositUseCase.perform(command));

        // Then
        var message = bankAccountNotFoundException.getMessage();
        Assertions.assertEquals(
                "Le compte bancaire avec la reference " + accountReference + " n'existe pas !", message);
    }
}
