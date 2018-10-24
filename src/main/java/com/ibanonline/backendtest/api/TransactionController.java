package com.ibanonline.backendtest.api;

import com.google.common.base.Preconditions;
import com.ibanonline.backendtest.api.dtos.GetTransactionsRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import static com.ibanonline.backendtest.api.dtos.Operation.RANGE;

/**
 * @author Ricardo Bernardino
 */
@Slf4j
@RestController
public class TransactionController {

    @RequestMapping(
            value = "/user/{userId}/transactions",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public void listUserTransactions(
            @PathVariable("userId") String userId,
            @RequestBody GetTransactionsRequestDto request) {

        if (request.getOperation() == RANGE) {
            Preconditions.checkNotNull(request.getAmountFrom(), "Amount from cannot be null for range operations");
            Preconditions.checkNotNull(request.getAmountTo(), "Amount to cannot be null for range operations");
        }

        System.out.println("USER ID: " + userId);
        System.out.println("Request: " + request);

    }
}
