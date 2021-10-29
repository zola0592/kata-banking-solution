package org.kata.banking.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kata.banking.exception.DepositOrWithDrawAmountIsNegativeException;
import org.kata.banking.exception.WithdrawAmountGreaterThanBalanceException;

import java.util.UUID;

/**
 * <p>Test unitaire de l'entité domaine {@link BankAmount}.</p>
 *
 * @author Ahmed Ali DAO 2021-10-29
 */
class AccountTest {

    final String reference = UUID.randomUUID().toString();
    Account account;

    @BeforeEach
    void setUp() {
        this.account = new BankAccount(reference, 500);
    }

    @Test
    @DisplayName("Test la récupération de la référence unique d'un compte")
    void accountReference() {
        // Given

        // When
        var accountReference = this.account.accountReference();

        // Then
        Assertions.assertNotNull(accountReference);
        Assertions.assertEquals(reference, accountReference);
    }

    @Test
    @DisplayName("Test le dépôt d'argent dans un compte")
    void deposit() {
        // Given
        double value = 100.0;
        var amount = new BankAmount(value);

        // When
        var transaction = this.account.deposit(amount);
        var balance = this.account.balance();

        // Then
        Assertions.assertEquals(transaction.amount(), amount);
        Assertions.assertEquals(transaction.balanceAfterTransaction(), balance);
    }

    @Test
    @DisplayName("Test l'impossibilité d'effectuer le dépôt d'un montant negatif dans un compte")
    void depositNegativeAmount() {
        // Given
        double value = -100.0;
        var amount = new BankAmount(value);

        // When
        var depositOrWithDrawAmountIsNegativeException = Assertions
                .assertThrows(DepositOrWithDrawAmountIsNegativeException.class, () -> this.account.deposit(amount));

        // Then
        var message = depositOrWithDrawAmountIsNegativeException.getMessage();
        Assertions.assertEquals(
                "Le montant du depot doit etre positif !", message);
    }

    @Test
    @DisplayName("Test le retrait d'argent dans un compte")
    void withdraw() {
        // Given
        double value = 100.0;
        var amount = new BankAmount(value);

        // When
        var transaction = this.account.withdraw(amount);
        var balance = this.account.balance();

        // Then
        Assertions.assertNotNull(balance);
        Assertions.assertEquals(transaction.amount(), amount);
        Assertions.assertEquals(transaction.balanceAfterTransaction(), balance);
    }

    @Test
    @DisplayName("Test l'impossibilité d'effectuer le retrait d'un montant negatif dans un compte")
    void withdrawNegativeAmount() {
        // Given
        double value = -100.0;
        var amount = new BankAmount(value);

        // When
        var depositOrWithDrawAmountIsNegativeException = Assertions
                .assertThrows(DepositOrWithDrawAmountIsNegativeException.class, () -> this.account.withdraw(amount));

        // Then
        var message = depositOrWithDrawAmountIsNegativeException.getMessage();
        Assertions.assertEquals(
                "Le montant du retrait doit etre positif !", message);
    }

    @Test
    @DisplayName("Test l'impossibilité d'effectuer le retrait d'un montant superieur au solde du compte")
    void withdrawAmountGreaterThanAccountBalance() {
        // Given
        double value = 1000.0;
        var amount = new BankAmount(value);

        // When
        var withdrawAmountGreaterThanBalanceException = Assertions
                .assertThrows(WithdrawAmountGreaterThanBalanceException.class, () -> this.account.withdraw(amount));

        // Then
        var message = withdrawAmountGreaterThanBalanceException.getMessage();
        Assertions.assertEquals(
                "Pas assez de fonds pour effectuer ce retrait, votre solde est de : " + account.balance().moneyRepresentation() + " !", message);
    }


    @Test
    @DisplayName("Test la récupération et la cohérence de la balance d'un compte")
    void balance() {
        // Given
        double value = 100.0;
        var amount = new BankAmount(value);

        // When
        var transaction = this.account.deposit(amount);
        var balance = this.account.balance();

        // Then
        Assertions.assertNotNull(balance);
        Assertions.assertEquals(transaction.balanceAfterTransaction(), balance);
    }

    @Test
    @DisplayName("Test la récupération actuel du montant d'un compte")
    void printStatement() {
        // Given
        double value = 100.0;
        var amount = new BankAmount(value);

        // When
        var transaction = this.account.deposit(amount);

        // Then
        var statement = this.account.printStatement();
        Assertions.assertNotNull(statement);
        Assertions.assertTrue(statement.contains(transaction.print()));
        var actualBalance = transaction.balanceAfterTransaction();
        Assertions.assertTrue(statement.contains("Solde du compte : " + actualBalance.moneyRepresentation()));
    }

}
