package com.ibanonline.backendtest.domain;

import com.ibanonline.backendtest.api.dtos.Operation;
import com.ibanonline.backendtest.api.dtos.UnitDto;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Ricardo Bernardino
 */
@Data
@Builder
public class TransactionsRequest {
    private UnitDto unit;
    private int duration;
    private Operation operation;
    private BigDecimal amount;
    private BigDecimal amountFrom;
    private BigDecimal amountTo;
}
