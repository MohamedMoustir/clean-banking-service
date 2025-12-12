package com.banking.domain;

public interface AccountService {
    void deposit(int amount);
    void withdraw(int amount);
    void printStatement();
}