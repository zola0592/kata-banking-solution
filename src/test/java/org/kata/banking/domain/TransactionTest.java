package org.kata.banking.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * <p>Test unitaire de l'entité domaine {@link BankTransaction}.</p>
 *
 * @author Ahmed Ali DAO 2021-10-29
 */
class TransactionTest {

    @Test
    @DisplayName("Test la récupération d'une date de transaction qui a eu lieu")
    void transactionDate() {
        // Given
        var transactionAmount = new BankAmount(100.0);
        var balance = new BankAmount(0.0);

        // When
        var transaction = new BankTransaction(transactionAmount, balance);
        var transactionDate = transaction.transactionDate();

        // Then
        Assertions.assertNotNull(transactionDate);
    }

    @Test
    @DisplayName("Test la récupération de la balance après qu'une transaction est eu lieu")
    void balanceAfterTransaction() {
        // Given
        var transactionAmount = new BankAmount(100.0);
        var balance = new BankAmount(0.0);

        // When
        var transaction = new BankTransaction(transactionAmount, balance);
        var balanceAfterTransaction = transaction.balanceAfterTransaction();

        // Then
        Assertions.assertEquals(transactionAmount, balanceAfterTransaction);
    }

    @Test
    @DisplayName("Test la récupération de la balance avant qu'une transaction est eu lieu")
    void balanceBeforeTransaction() {
        // Given
        var transactionAmount = new BankAmount(100.0);
        var balance = new BankAmount(0.0);

        // When
        var transaction = new BankTransaction(transactionAmount, balance);
        var balanceBeforeTransaction = transaction.balanceBeforeTransaction();

        // Then
        Assertions.assertEquals(balance, balanceBeforeTransaction);
    }

    @Test
    @DisplayName("Test l'affichage des informations d'une transaction")
    void print() {
        // Given
        var transactionAmount = new BankAmount(100.0);
        var balance = new BankAmount(0.0);

        // When
        var transaction = new BankTransaction(transactionAmount, balance);
        var print = transaction.print();

        // Then
        Assertions.assertTrue(print.contains("Operation : "
                + transaction.operation()
                + " || Date : "
                + transaction.transactionDate()
                + " || Amount : "
                + transaction.amount().moneyRepresentation()
                + " || Balance : "
                + transaction.balanceAfterTransaction().moneyRepresentation()
                + "\n"));
    }
}
