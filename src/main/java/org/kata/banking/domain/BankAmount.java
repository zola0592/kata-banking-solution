package org.kata.banking.domain;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * <p>Entité domaine contenant les informations d'un montant.</p>
 *
 * @author Ahmed Ali DAO 2021-10-29
 */
public final class BankAmount implements Amount {

    private final BigDecimal value;

    public BankAmount(double value) {
        this.value = BigDecimal.valueOf(value);
    }

    public BankAmount() {
        this.value = BigDecimal.ZERO;
    }

    @Override
    public Amount plus(Amount otherAmount) {
        BigDecimal otherValue = otherAmount.value();
        BigDecimal newValue = this.value.add(otherValue);
        var doubleValue = newValue.doubleValue();
        return new BankAmount(doubleValue);
    }

    @Override
    public Amount negative() {
        BigDecimal negateValue = this.value.negate();
        var doubleValue = negateValue.doubleValue();
        return new BankAmount(doubleValue);
    }

    @Override
    public boolean isGreaterThan(Amount otherAmount) {
        BigDecimal otherValue = otherAmount.value();
        var result = this.value.compareTo(otherValue);
        return result > 0;
    }

    @Override
    public String moneyRepresentation() {
        return this.value.toString() + " FCFA";
    }

    @Override
    public BigDecimal value() {
        return this.value;
    }

    @Override
    public boolean isNegative() {
        return this.value.signum() == -1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Amount)) {
            return false;
        }
        BankAmount amount = (BankAmount) o;
        return value.equals(amount.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "BankAmount{" +
                "value=" + value +
                '}';
    }
}
