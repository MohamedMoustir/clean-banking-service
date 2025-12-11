package com.banking;

import com.banking.domain.DateProvider;
import com.banking.domain.StatementPrinter;
import com.banking.domain.Transaction;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class AccountAcceptanceTest {

    @Test
    void should_print_statement_containing_all_transactions() {
        DateProvider dateMock = new DateProvider() {
            private final Queue<LocalDate> dates = new LinkedList<>(List.of(
                    LocalDate.of(2012, 1, 10),
                    LocalDate.of(2012, 1, 13),
                    LocalDate.of(2012, 1, 14)
            ));

            @Override
            public LocalDate today() {
                return dates.poll();
            }
        };

        StatementPrinter printerMock = new StatementPrinter() {
            public List<Transaction> printedTransactions = new ArrayList<>();

            @Override
            public void print(List<Transaction> transactions) {
                this.printedTransactions = transactions;
            }
        };
    }


}