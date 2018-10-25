package com.ibanonline.backendtest.domain;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @author Ricardo Bernardino
 */
@Data
@Builder
public class Transaction {
    private String transactionId;
    private String userId;
    private TransactionType transactionType;
    private String fromUser;
    private String toUser;
    private String currency;
    private BigDecimal amount;
    private Timestamp timestamp;
    private String description;
}
