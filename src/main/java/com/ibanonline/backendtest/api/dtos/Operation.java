package com.ibanonline.backendtest.api.dtos;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author Ricardo Bernardino
 */
public enum Operation {
    EQ("eq"),
    GT("gt"),
    GTE("gte"),
    LT("lt"),
    LTE("lte"),
    RANGE("range");

    @JsonValue
    private String operation;

    Operation(String operation) {
        this.operation = operation;
    }

    @Override
    public String toString() {
        return operation;
    }
}
