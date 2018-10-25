package com.ibanonline.backendtest.api.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author Ricardo Bernardino
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetTransactionSumResponseDto {
    private BigDecimal sumAmount;
}
