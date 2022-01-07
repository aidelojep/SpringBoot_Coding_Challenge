package com.example.transactionservice.service;

import com.example.transactionservice.data.dao.TransactionStat;
import com.example.transactionservice.data.model.Transaction;
import com.example.transactionservice.data.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService{
    private final TransactionRepository transactionRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = TransactionRepository.getInstance();
    }

    @Override
    public Transaction save(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    @Override
    public void deleteAllTransactions() {
        transactionRepository.deleteAll();
    }

    @Override
    public TransactionStat getTransactionStats() {
        return transactionRepository.getStats();
    }
}
