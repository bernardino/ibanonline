package com.ibanonline.backendtest.domain;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author Ricardo Bernardino
 */
public enum TransactionType {
    WITHDRAWAL("withdrawal"),
    CREDIT("credit"),
    DEBIT("debit");

    @JsonValue
    private String transactionType;

    TransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    @Override
    public String toString() {
        return transactionType;
    }
}
