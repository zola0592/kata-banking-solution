package org.kata.banking.domain;

import org.kata.banking.exception.DepositOrWithDrawAmountIsNegativeException;
import org.kata.banking.exception.WithdrawAmountGreaterThanBalanceException;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * <p>Entité domaine contenant les informations d'un compte bancaire.</p>
 *
 * @author Ahmed Ali DAO 2021-10-29
 */
public final class BankAccount implements Account {

    private final String reference;
    private final List<Amount> amounts;
    private final List<Transaction> transactions;

    public BankAccount(String reference) {
        this.reference = reference;
        this.amounts = new LinkedList<>();
        this.transactions = new LinkedList<>();
    }

    public BankAccount(String reference, double initialBalance) {
        this(reference);
        var amount = new BankAmount(initialBalance);
        this.amounts.add(amount);
    }

    @Override
    public String accountReference() {
        return reference;
    }

    @Override
    public Transaction deposit(Amount amount) {
        if (amount.isNegative()) {
            throw new DepositOrWithDrawAmountIsNegativeException("Le montant du depot doit etre positif !");
        }
        Transaction transaction = new BankTransaction(amount, this.balance());
        this.transactions.add(transaction);
        var newAmount = transaction.balanceAfterTransaction();
        this.amounts.add(newAmount);
        return transaction;
    }

    @Override
    public Transaction withdraw(Amount amount) {

        if (amount.isNegative()) {
            throw new DepositOrWithDrawAmountIsNegativeException("Le montant du retrait doit etre positif !");
        }

        var balance = this.balance();

        if (amount.isGreaterThan(balance)) {
            throw new WithdrawAmountGreaterThanBalanceException("Pas assez de fonds pour effectuer ce retrait, votre solde est de : " + balance.moneyRepresentation() + " !");
        }

        var negativeAmount = amount.negative();
        var transaction = new BankTransaction(negativeAmount, this.balance());
        this.transactions.add(transaction);
        var newBalance = transaction.balanceAfterTransaction();
        this.amounts.add(newBalance);
        return transaction;
    }

    @Override
    public Amount balance() {
        var size = this.amounts.size();
        if (size > 0) {
            return this.amounts.get(size - 1);
        } else {
            return new BankAmount();
        }
    }

    @Override
    public String printStatement() {
        var stringBuilder = new StringBuilder();

        transactions.forEach(transaction -> {
            String print = transaction.print();
            stringBuilder.append(print);
        });

        stringBuilder.append("Solde du compte : ")
                .append(this.balance().moneyRepresentation());
        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BankAccount)) {
            return false;
        }
        BankAccount that = (BankAccount) o;
        return reference.equals(that.reference);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reference);
    }
}
