package org.kata.banking;

import org.kata.banking.domain.Account;
import org.kata.banking.domain.BankAccount;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MemoryRepository implements Repository {

    private final Map<String, Account> accounts;

    public MemoryRepository() {
        this(500.0);
    }

    public MemoryRepository(double initialBalance) {
        this.accounts = new HashMap<>();
        populate(initialBalance);
    }

    @Override
    public Optional<Account> findAccountByReference(String reference) {
        var account = accounts.get(reference);
        return Optional.ofNullable(account);
    }

    private void populate(double initialBalance) {
        for (int i = 1; i < 11; i++) {
            var reference = String.valueOf(i);
            var account = new BankAccount(reference, initialBalance);
            accounts.put(reference, account);
        }
    }
}
