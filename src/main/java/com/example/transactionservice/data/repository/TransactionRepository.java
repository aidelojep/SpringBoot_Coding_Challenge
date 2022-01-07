package com.example.transactionservice.data.repository;

import com.example.transactionservice.data.dao.TransactionStat;
import com.example.transactionservice.data.model.Transaction;
import com.example.transactionservice.exception.FutureTransactionException;
import com.example.transactionservice.exception.StaleTransactionException;
import com.example.transactionservice.exception.TransactionException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

@Component
public class TransactionRepository {
    private int idCount;
    private final ConcurrentMap<Long, Transaction> transactiondb;
    public static TransactionRepository instance;


    private TransactionRepository() {
        transactiondb = new ConcurrentHashMap<>();
    }

    private Long generateId(){
        return (long) (transactiondb.size() + 1);
    }

    public int getSize(){
        return transactiondb.size();
    }
    public Transaction save(Transaction transaction){
        long timeLapse = Duration.between(transaction.getTimestamp(), LocalDateTime.now()).toSeconds();
        if(timeLapse > 30)
            throw new StaleTransactionException("Transaction over 30seconds cannot be registered");
        if(timeLapse < 0)
            throw new FutureTransactionException("Transaction cannot be done in the future");
        long id = generateId();
        transaction.setId(id);
        transactiondb.put(id, transaction);
        return transaction;
    }

    public List<Transaction> findAll(){

        return transactiondb.values().stream().collect(Collectors.toList());
    }

    public void deleteAll(){
        transactiondb.clear();
    }

    public TransactionStat getStats(){
        if(transactiondb.isEmpty()) return new TransactionStat();
        List<BigDecimal> t = transactiondb.values().stream().filter(e ->
                Duration.between(e.getTimestamp(), LocalDateTime.now()).toSeconds() > 30
        ).map(Transaction::getAmount).collect(Collectors.toList());
        BigDecimal sum = t.stream().parallel().reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal max = t.stream().parallel().max(BigDecimal::compareTo).get();
        BigDecimal min = t.stream().parallel().min(BigDecimal::compareTo).get();
        TransactionStat stat = TransactionStat
                .builder()
                .sum(sum)
                .avg(sum.divide(BigDecimal.valueOf(t.size())))
                .count((long) t.size())
                .max(max)
                .min(min)
                .build();

        return stat;
    }



    private static class TransactionRepositorySingletonHelper{
        private static final TransactionRepository instance = new TransactionRepository();
    }

    public static TransactionRepository getInstance(){
        return TransactionRepositorySingletonHelper.instance;
    }
}
