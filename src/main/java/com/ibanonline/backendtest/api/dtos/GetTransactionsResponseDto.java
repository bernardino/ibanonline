package com.ibanonline.backendtest.api.dtos;

import lombok.Data;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

/**
 * @author Ricardo Bernardino
 */
@Data
public class GetTransactionsResponseDto {
    private String userId;
    private TransactionType transactionType;
    private String fromUser;
    private String toUser;
    private String currency;
    private BigDecimal amount;
    private ZonedDateTime timestamp;
    private String description;
}
