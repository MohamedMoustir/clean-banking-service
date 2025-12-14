package com.banking.domain.Exception;

public class BankingException extends RuntimeException {
    public BankingException(String message) {
        super(message);
    }
}