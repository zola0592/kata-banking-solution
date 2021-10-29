package org.kata.banking.domain;

/**
 * <p>Enumeration contenant les type d'operation (DEPOT, RETRAIT) pour une transaction bancaire.</p>
 *
 * @author Ahmed Ali DAO 2021-10-29
 */
public enum Operation {
    DEPOSIT {
        @Override
        public String toString() {
            return "Dépôt";
        }
    },
    WITHDRAW {
        @Override
        public String toString() {
            return "Retrait";
        }
    }
}
