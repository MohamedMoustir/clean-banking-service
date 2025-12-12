package com.banking.domain;

import com.banking.domain.Exception.BankingException;

import java.util.ArrayList;
import java.util.List;

public class Account implements AccountService {

    private final DateProvider dateProvider;
    private final StatementPrinter printer;
    private final List<Transaction> transactions = new ArrayList<>();
    private int balance = 0;

    public Account(DateProvider dateProvider, StatementPrinter printer) {
        this.dateProvider = dateProvider;
        this.printer = printer;
    }

}