package org.kata.banking.domain;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * <p>Entité domaine contenant les informations d'une transaction d'un compte bancaire.</p>
 *
 * @author Ahmed Ali DAO 2021-10-29
 */
public final class BankTransaction implements Transaction {

    private final LocalDateTime dateTime;
    private final Amount amount;
    private final Amount balanceBeforeTransaction;

    public BankTransaction(Amount transactionAmount, Amount balance) {
        this.balanceBeforeTransaction = balance;
        this.amount = transactionAmount;
        this.dateTime = LocalDateTime.now();
    }

    @Override
    public Operation operation() {
        return (amount.isNegative() ? Operation.WITHDRAW : Operation.DEPOSIT);
    }

    @Override
    public String transactionDate() {
        return this.dateTime.toString();
    }

    @Override
    public Amount balanceAfterTransaction() {
        return this.balanceBeforeTransaction.plus(this.amount);
    }

    @Override
    public Amount balanceBeforeTransaction() {
        return this.balanceBeforeTransaction;
    }

    @Override
    public Amount amount() {
        return (amount.isNegative() ? amount.negative() : amount);
    }

    @Override
    public String print() {
        return "Operation : "
                + this.operation().toString()
                + " || Date : "
                + this.dateTime.toString()
                + " || Amount : "
                + amount().moneyRepresentation()
                + " || Balance : "
                + balanceAfterTransaction().moneyRepresentation()
                + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BankTransaction)) {
            return false;
        }
        BankTransaction that = (BankTransaction) o;
        return dateTime.equals(that.dateTime) && amount
                .equals(that.amount) && balanceBeforeTransaction
                .equals(that.balanceBeforeTransaction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateTime, amount, balanceBeforeTransaction);
    }
}


