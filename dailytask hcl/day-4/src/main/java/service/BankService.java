package service;

import model.BankAccount;

public class BankService {

    public void deposit(BankAccount account, double amount) {
        account.deposit(amount);
    }

    public boolean withdraw(BankAccount account, double amount) {
        return account.withdraw(amount);
    }

    public void printAccountDetails(BankAccount account) {
        System.out.println("Account Number : " + account.getAccountNumber());
        System.out.println("Account Holder : " + account.getAccountHolder());
        System.out.println("Balance        : ₹" + account.getBalance());
    }
}