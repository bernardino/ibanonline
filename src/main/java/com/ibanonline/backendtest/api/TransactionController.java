package com.ibanonline.backendtest.api;

import com.google.common.base.Preconditions;
import com.ibanonline.backendtest.api.dtos.GetTransactionSumResponseDto;
import com.ibanonline.backendtest.api.dtos.GetTransactionsRequestDto;
import com.ibanonline.backendtest.api.dtos.GetTransactionsResponseDto;
import com.ibanonline.backendtest.domain.Transaction;
import com.ibanonline.backendtest.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

import static com.ibanonline.backendtest.api.dtos.Operation.RANGE;

/**
 * @author Ricardo Bernardino
 */
@Slf4j
@RestController
public class TransactionController {

    private TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @RequestMapping(
            value = "/user/{userId}/transactions",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public GetTransactionsResponseDto listUserTransactions(
            @PathVariable("userId") String userId,
            @RequestBody GetTransactionsRequestDto request) {

        validate(request);



        final List<Transaction> transactions = transactionService.getTransactions(userId, request.convert());

        return GetTransactionsResponseDto.builder()
                .transactions(transactions)
                .build();
    }

    @RequestMapping(
            value = "/user/{userId}/transactions/sum",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public GetTransactionSumResponseDto getUserTransactionsSum(
            @PathVariable("userId") String userId,
            @RequestBody GetTransactionsRequestDto request) {

        validate(request);

        BigDecimal sumAmount = transactionService.getTransactions(userId, request.convert()).stream()
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return GetTransactionSumResponseDto.builder()
                .sumAmount(sumAmount)
                .build();
    }

    private void validate(GetTransactionsRequestDto request) {
        Preconditions.checkArgument(request != null, "Request body must not be null");

        if (request.getOperation() == RANGE) {
            Preconditions.checkNotNull(request.getAmountFrom(), "Amount from cannot be null for range operations");
            Preconditions.checkNotNull(request.getAmountTo(), "Amount to cannot be null for range operations");

            Preconditions.checkState(request.getAmountFrom().compareTo(request.getAmountTo()) == -1, "amountFrom not less than amountTo");
        }
    }
}
