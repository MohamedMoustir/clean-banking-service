package com.banking;

import com.banking.domain.Account;
import com.banking.domain.AccountService;
import com.banking.domain.Exception.BankingException;
import com.banking.infrastructure.ConsoleStatementPrinter;
import com.banking.infrastructure.SystemDateProvider;

import java.util.Scanner;
import java.util.logging.Logger;

public class Main {

    private static final Logger logger = Logger.getLogger(Main.class.getName());
    private static final String CYAN = "\u001B[36m";
    private static final String YELLOW = "\u001B[33m";
    private static final String RESET = "\u001B[0m";
    public static void main(String[] args) {

        logger.info("Application started.");

        AccountService myAccount = new Account(
                new SystemDateProvider(),
                new ConsoleStatementPrinter()
        );

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("=== Welcome to the Banking App ===");

        while (running) {
            printMenu();
            String input = scanner.next();

            logger.info("User selected menu option: " + input);

            try {
                switch (input) {
                    case "1" -> {
                        System.out.print("Enter deposit amount: ");
                        int amount = scanner.nextInt();
                        logger.info("User deposit request: " + amount);
                        myAccount.deposit(amount);
                    }
                    case "2" -> {
                        System.out.print("Enter withdrawal amount: ");
                        int amount = scanner.nextInt();
                        logger.info("User withdrawal request: " + amount);
                        myAccount.withdraw(amount);
                    }
                    case "3" -> {
                        logger.info("User requested bank statement");
                        myAccount.printStatement();
                    }
                    case "4" -> {
                        logger.info("User exited the application.");
                        running = false;
                    }
                    default -> {
                        logger.warning("User entered invalid option: " + input);
                        System.out.println("Invalid choice.");
                    }
                }
            } catch (BankingException e) {
                logger.severe("Banking error: " + e.getMessage());
                System.out.println("Banking Error: " + e.getMessage());
            } catch (Exception e) {
                logger.severe("Input error: " + e.getMessage());
                System.out.println("Input Error: Invalid number.");
                scanner.nextLine();
            }
        }

        logger.info("Application closed.");
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n" + CYAN + "--- Main Menu ---" + RESET);
        System.out.println("1. " + YELLOW + "Deposit Money" + RESET);
        System.out.println("2. " + YELLOW + "Withdraw Money" + RESET);
        System.out.println("3. " + YELLOW + "View Statement" + RESET);
        System.out.println("4. Exit");
        System.out.print("Select an option: ");
    }

}
