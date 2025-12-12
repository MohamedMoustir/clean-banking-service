package com.banking.domain;

import java.util.List;

public interface StatementPrinter {
    void print(List<Transaction> transactions);
}