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

    @Override
    public void deposit(int amount) {
        if (amount <= 0) {
            throw new BankingException("Amount must be positive.");
        }
        balance += amount;
        transactions.add(new Transaction(dateProvider.today(), amount, balance));
    }

    @Override
    public void withdraw(int amount) {
        if (amount <= 0) {
            throw new BankingException("Amount must be positive.");
        }
        if (balance < amount) {
            throw new BankingException("Insufficient balance.");
        }
        balance -= amount;
        transactions.add(new Transaction(dateProvider.today(), -amount, balance));
    }

    @Override
    public void printStatement() {
        printer.print(transactions);
    }
}