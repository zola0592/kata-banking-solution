package org.kata.banking.domain;

import java.math.BigDecimal;

/**
 * <p>Interface de base contenant les operations possibles à faire sur le montant.</p>
 *
 * @author Ahmed Ali DAO 2021-10-29
 */
public interface Amount {

    /**
     * <p>Additionne un autre montant au montant actuel.</p>
     *
     * @param otherAmount Montant a ajouté
     * @return Le nouveau montant
     */
    Amount plus(final Amount otherAmount);

    /**
     * <p>Retourne la valeur négative du montant</p>
     *
     * @return La valeur négative du montant
     */
    Amount negative();

    /**
     * @param otherAmount Montant a comparé
     * @return Vrai si le montant acutel est supérieur au montant passé en paramètre
     */
    boolean isGreaterThan(final Amount otherAmount);

    /**
     * @return La représentation du montant
     */
    String moneyRepresentation();

    /**
     * @return La valeur brute du montant
     */
    BigDecimal value();

    /**
     * @return Vrai si le montant acutel est negative
     */
    boolean isNegative();
}
