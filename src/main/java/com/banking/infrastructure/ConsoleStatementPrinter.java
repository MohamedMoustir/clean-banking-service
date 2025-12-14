package com.banking.infrastructure;

import com.banking.domain.StatementPrinter;
import com.banking.domain.Transaction;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ConsoleStatementPrinter implements StatementPrinter {

    private static final String HEADER = "Date       | Amount | Balance";
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    @Override
    public void print(List<Transaction> transactions) {
        System.out.println(HEADER);

        List<Transaction> copy = new ArrayList<>(transactions);
        Collections.reverse(copy);

        for (Transaction t : copy) {
            System.out.println(formatLine(t));
        }
    }

    private String formatLine(Transaction t) {
        return t.date().format(DATE_FORMAT) + " | " + t.amount() + " | " + t.balance();
    }
}