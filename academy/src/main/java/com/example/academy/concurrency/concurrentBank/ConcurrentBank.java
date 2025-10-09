package com.example.academy.concurrency.concurrentBank;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ConcurrentBank {
    private final List<BankAccount> accounts = new CopyOnWriteArrayList<>();
    private static int accountCounter = 0;

    public BankAccount createAccount(double initialBalance) {
        accountCounter = accountCounter + 1;
        String accountNumber = "Account# " + accountCounter;
        BankAccount account = new BankAccount(accountNumber, initialBalance);
        accounts.add(account);
        return account;
    }

    public boolean transfer(BankAccount fromAccount, BankAccount toAccount, double amount) {
        BankAccount first = fromAccount;
        BankAccount second = toAccount;

        if (first.getAccountNumber().compareTo(second.getAccountNumber()) > 0) {
            first = toAccount;
            second = fromAccount;
        }

        synchronized (first) {
            synchronized (second) {
                if (fromAccount.withdraw(amount)) {
                    toAccount.deposit(amount);
                    return true;
                }
                return false;
            }
        }
    }

    public BigDecimal getTotalBalance() {
        BigDecimal total = BigDecimal.valueOf(0);
        for (BankAccount account : accounts) {
            total = total.add(account.getBalance());
        }
        return total;
    }
}