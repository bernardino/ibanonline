package com.ibanonline.backendtest.api.dtos;

import com.ibanonline.backendtest.domain.Transaction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Ricardo Bernardino
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetTransactionsResponseDto {
    private List<Transaction> transactions;
}
