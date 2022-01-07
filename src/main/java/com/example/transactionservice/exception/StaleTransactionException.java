package com.example.transactionservice.exception;

public class StaleTransactionException extends ApplicationException{

    public StaleTransactionException(String message) {
        super(message);
    }

    public StaleTransactionException(String message, Throwable cause) {
        super(message, cause);
    }

    public StaleTransactionException(Throwable cause) {
        super(cause);
    }
}
