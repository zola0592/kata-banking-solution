package org.kata.banking.exception;

import org.kata.banking.domain.Amount;

/**
 * <p>Exception se levant lorsque le {@link Amount} du depot / retrait est negative.</p>
 *
 * @author Ahmed Ali DAO 2021-10-29
 */
public class DepositOrWithDrawAmountIsNegativeException extends RuntimeException {
    public DepositOrWithDrawAmountIsNegativeException(String message) {
        super(message);
    }
}
