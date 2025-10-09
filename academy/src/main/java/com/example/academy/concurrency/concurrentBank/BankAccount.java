package com.example.academy.concurrency.concurrentBank;


import java.math.BigDecimal;

public class BankAccount {
    private final String accountNumber;
    private BigDecimal balance;

    public BankAccount(String accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = BigDecimal.valueOf(initialBalance);
    }

    public synchronized void deposit(double amount) {
        BigDecimal amountBD = BigDecimal.valueOf(amount);
        if (amount > 0) {
            balance = balance.add(amountBD);
        }
    }

    public synchronized boolean withdraw(double amount) {
        BigDecimal amountBD = BigDecimal.valueOf(amount);
        if (amount > 0 && balance.compareTo(amountBD)>0) {
            balance = balance.subtract(amountBD);
            return true;
        }
        return false;
    }

    public synchronized BigDecimal getBalance() {
        return balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }
}
