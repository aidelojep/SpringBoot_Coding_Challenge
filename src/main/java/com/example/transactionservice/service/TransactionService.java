package com.example.transactionservice.service;

import com.example.transactionservice.data.dao.TransactionStat;
import com.example.transactionservice.data.model.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TransactionService {
    Transaction save(Transaction transaction);
    List<Transaction> getAllTransactions();
    void deleteAllTransactions();
    TransactionStat getTransactionStats();
}
