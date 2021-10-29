package org.kata.banking.domain;

/**
 * <p>Interface de base contenant les operations possibles à faire sur une transaction bancaire.</p>
 *
 * @author Ahmed Ali DAO 2021-10-29
 */
public interface Transaction {

    /**
     * <p>Retourne le type d'operation(Retrait / Depot) de la transaction.</p>
     *
     * @return le type d'operation(Retrait / Depot) de la transaction
     */
    Operation operation();

    /**
     * <p>Retourne la date de la transaction.</p>
     *
     * @return La date de la transaction
     */
    String transactionDate();

    /**
     * <p>Retourne la balance après la transaction.</p>
     *
     * @return La balance après la transaction
     */
    Amount balanceAfterTransaction();

    /**
     * <p>Retourne la balance avant la transaction.</p>
     *
     * @return La balance avant la transaction
     */
    Amount balanceBeforeTransaction();

    /**
     * <p>Retourne le montant de la transaction.</p>
     *
     * @return Le montant de la transaction
     */
    Amount amount();

    /**
     * <p>Retourne la representation de la transaction.</p>
     *
     * @return La representation de la transaction
     */
    String print();
}
