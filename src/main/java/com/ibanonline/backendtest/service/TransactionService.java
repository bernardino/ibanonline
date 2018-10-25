package com.ibanonline.backendtest.service;

import com.ibanonline.backendtest.api.dtos.GetTransactionsRequestDto;
import com.ibanonline.backendtest.domain.Transaction;
import com.ibanonline.backendtest.domain.TransactionsRequest;
import com.ibanonline.backendtest.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Ricardo Bernardino
 */
@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<Transaction> getTransactions(String userId, TransactionsRequest request) {
        return transactionRepository.getTransactions(userId, request);
    }
}
