package org.kata.banking.usecase;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kata.banking.MemoryRepository;
import org.kata.banking.Repository;
import org.kata.banking.UseCase;
import org.kata.banking.command.MakeWithdrawalCommand;
import org.kata.banking.domain.Account;
import org.kata.banking.domain.Transaction;
import org.kata.banking.exception.BankAccountNotFoundException;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * <p>Test unitaire du cas d'utilisation {@link MakeWithdrawal}.</p>
 *
 * @author Ahmed Ali DAO 2021-10-29
 */
class MakeWithdrawalTest {

    UseCase<Transaction, MakeWithdrawalCommand> makeWithdrawalUseCase;
    Repository repository;
    double initialBalance = 500.0;

    @BeforeEach
    void setUp() {
        this.repository = new MemoryRepository(initialBalance);
        this.makeWithdrawalUseCase = new MakeWithdrawal(this.repository);
    }

    @Test
    @DisplayName("Test le retrait d'argent dans un compte récupérer dans une DB à partir de sa référence unique")
    void makeWithdrawal() {
        // Given
        final var amountToWithdraw = 100.0;
        final var accountReference = "2";

        final var realBalance = BigDecimal.valueOf(this.initialBalance - amountToWithdraw);
        var command = new MakeWithdrawalCommand(accountReference, amountToWithdraw);

        // When
        this.makeWithdrawalUseCase.perform(command);

        // Then
        Optional<Account> accountOptional = this.repository.findAccountByReference(accountReference);
        Assertions.assertTrue(accountOptional.isPresent());
        var account = accountOptional.get();
        var currentBalance = account.balance();
        Assertions.assertEquals(realBalance, currentBalance.value());
    }

    @Test
    @DisplayName("Test l'impossibilité d'effectuer un retrait d'argent dans un compte qui ne possède pas une balance suffisante")
    void impossibleMakeWithdrawal() {
        // Given
        final String accountReference = "100";
        final var amountToWithdraw = 400.0;

        var command = new MakeWithdrawalCommand(accountReference, amountToWithdraw);

        // When
        var bankAccountNotFoundException = Assertions
                .assertThrows(BankAccountNotFoundException.class,
                        () -> this.makeWithdrawalUseCase.perform(command));

        // Then
        var message = bankAccountNotFoundException.getMessage();
        Assertions.assertEquals(
                "Le compte bancaire avec la reference " + accountReference + " n'existe pas !", message);
    }


}
