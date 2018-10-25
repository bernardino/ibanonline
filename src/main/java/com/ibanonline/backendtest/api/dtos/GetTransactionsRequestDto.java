package com.ibanonline.backendtest.api.dtos;

import com.ibanonline.backendtest.domain.TransactionsRequest;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Ricardo Bernardino
 */
@Data
@Builder
public class GetTransactionsRequestDto {
    private UnitDto unit;
    private int duration;
    private Operation operation;
    private BigDecimal amount;
    private BigDecimal amountFrom;
    private BigDecimal amountTo;

    public TransactionsRequest convert() {
        return TransactionsRequest.builder()
                .amount(amount)
                .amountFrom(amountFrom)
                .amountTo(amountTo)
                .duration(duration)
                .operation(operation)
                .unit(unit)
                .build();
    }
}
