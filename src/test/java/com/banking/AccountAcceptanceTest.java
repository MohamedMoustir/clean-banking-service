package com.banking;

import com.banking.domain.Account;
import com.banking.domain.DateProvider;
import com.banking.domain.StatementPrinter;
import com.banking.domain.Transaction;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.assertEquals;

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


        MockStatementPrinter printerMock = new MockStatementPrinter();

        Account account = new Account(dateMock, printerMock);

        account.deposit(1000);
        account.deposit(2000);
        account.withdraw(500);

        account.printStatement();


        assertEquals(3, printerMock.printedTransactions.size());

        assertEquals(1000, printerMock.printedTransactions.get(0).amount());
        assertEquals(LocalDate.of(2012, 1, 10), printerMock.printedTransactions.get(0).date());

        assertEquals(-500, printerMock.printedTransactions.get(2).amount());
        assertEquals(LocalDate.of(2012, 1, 14), printerMock.printedTransactions.get(2).date());
    }

    private static class MockStatementPrinter implements StatementPrinter {
        public List<Transaction> printedTransactions = new ArrayList<>();

        @Override
        public void print(List<Transaction> transactions) {
            this.printedTransactions = new ArrayList<>(transactions);
        }
    }
}