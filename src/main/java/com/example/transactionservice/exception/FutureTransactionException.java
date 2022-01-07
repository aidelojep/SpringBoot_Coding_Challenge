package com.example.transactionservice.exception;

public class FutureTransactionException extends ApplicationException{

    public FutureTransactionException(String message) {
        super(message);
    }

    public FutureTransactionException(String message, Throwable cause) {
        super(message, cause);
    }

    public FutureTransactionException(Throwable cause) {
        super(cause);
    }
}
