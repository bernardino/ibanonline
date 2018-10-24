package com.ibanonline.backendtest.api.dtos;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Ricardo Bernardino
 */
@Data
public class GetTransactionsRequestDto {
    private UnitDto unit;
    private int duration;
    private Operation operation;
    private BigDecimal amount;
    private BigDecimal amountFrom;
    private BigDecimal amountTo;

}
