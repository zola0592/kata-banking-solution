package org.kata.banking.domain;

/**
 * <p>Interface de base contenant les operations possibles à faire sur un compte bancaire.</p>
 *
 * @author Ahmed Ali DAO 2021-10-29
 */
public interface Account {

    /**
     * @return Retourne la référence unique du compte
     */
    String accountReference();

    /**
     * <p>Effectue un dépôt d'argent sur le compte.</p>
     *
     * @param amount Montant à déposer.
     * @return {@link Transaction} : La ligne de la transaction.
     */
    Transaction deposit(Amount amount);

    /**
     * <p>Effectue un retrait d'argent sur le compte.</p>
     *
     * @param amount Montant à retirer.
     * @return {@link Transaction} : La ligne de la transaction.
     */
    Transaction withdraw(Amount amount);

    /**
     * @return {@link Amount} : Retourne le solde actuel du compte.
     */
    Amount balance();

    /**
     * @return L'historique des transactions du compte
     */
    String printStatement();
}
