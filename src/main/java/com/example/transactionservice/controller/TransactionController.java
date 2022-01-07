package com.example.transactionservice.controller;

import com.example.transactionservice.data.model.Transaction;
import com.example.transactionservice.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class TransactionController {


    private final TransactionService transactionService;

    @PostMapping("/transactions")
    public ResponseEntity<?> saveTransactions(@RequestBody Transaction transaction){
        Transaction t = transactionService.save(transaction);
        return ResponseEntity.ok().body(t);
    }

    @GetMapping("/transactions")
    public ResponseEntity<?> getTransactions(){
        return ResponseEntity.ok().body(transactionService.getAllTransactions());
    }

    @DeleteMapping("/transactions")
    public ResponseEntity<?> deleteTransactions(){
        transactionService.deleteAllTransactions();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/statistics")
    public ResponseEntity<?> getTransactionStats(){
        return ResponseEntity.ok().body(transactionService.getTransactionStats());
    }
}
