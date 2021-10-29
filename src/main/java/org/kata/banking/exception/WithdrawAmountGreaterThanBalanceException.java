package org.kata.banking.exception;

import org.kata.banking.domain.Amount;

/**
 * <p>Exception se levant lorsque le {@link Amount} du retrait est superieur a la balance.</p>
 *
 * @author Ahmed Ali DAO 2021-10-29
 */
public class WithdrawAmountGreaterThanBalanceException extends RuntimeException {
    public WithdrawAmountGreaterThanBalanceException(String message) {
        super(message);
    }
}
