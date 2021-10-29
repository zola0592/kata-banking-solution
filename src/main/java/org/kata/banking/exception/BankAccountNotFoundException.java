package org.kata.banking.exception;

import org.kata.banking.domain.Account;

/**
 * <p>Exception se levant lorsqu'un {@link Account} est introuvable.</p>
 *
 * @author Ahmed Ali DAO 2021-10-29
 */
public class BankAccountNotFoundException extends RuntimeException {

    public BankAccountNotFoundException(String message) {
        super(message);
    }
}
