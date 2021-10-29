package org.kata.banking.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * <p>Test unitaire de l'entité domaine {@link BankAmount}.</p>
 *
 * @author Ahmed Ali DAO 2021-10-29
 */
class AmountTest {

    Amount amount;

    @BeforeEach
    void setUp() {
        this.amount = new BankAmount();
    }

    @Test
    @DisplayName("Test l'ajout l'addition deux 2 montants")
    void plus() {
        // Given
        Amount newAmount = new BankAmount(100.0);

        // When
        var currentAmount = this.amount.plus(newAmount);

        // Then
        Assertions.assertEquals(newAmount, currentAmount);
    }

    @Test
    @DisplayName("Test la récupération de la valeur négative d'un montant")
    void negative() {
        // Given
        double negativeAmount = -100.0;
        Amount newAmount = new BankAmount(100.0);

        // When
        var currentAmount = newAmount.negative();

        // Then
        Assertions.assertEquals(new BankAmount(negativeAmount), currentAmount);
    }

    @Test
    @DisplayName("Test la comparaison du plus grand entre 2 montants")
    void isGreaterThan() {
        // Given
        Amount newAmount = new BankAmount(200.0);

        // When
        var isGreaterThan = this.amount.isGreaterThan(newAmount);

        // Then
        Assertions.assertFalse(isGreaterThan);
    }

    @Test
    @DisplayName("Test la représentation textuel d'un montant")
    void moneyRepresentation() {
        // Given
        Amount newAmount = new BankAmount(100.0);

        // When
        var moneyRepresentation = newAmount.moneyRepresentation();

        // Then
        Assertions.assertEquals(newAmount.value() + " FCFA", moneyRepresentation);
    }
}
