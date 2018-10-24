package com.ibanonline.backendtest.api.dtos;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author Ricardo Bernardino
 */
public enum UnitDto {
    DAYS("days"),
    WEEKS("weeks"),
    MONTHS("months");

    @JsonValue
    private String range;

    UnitDto(String range) {
        this.range = range;
    }

    @Override
    public String toString() {
        return range;
    }
}
