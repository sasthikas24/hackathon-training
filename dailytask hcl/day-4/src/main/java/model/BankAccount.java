package model;

import java.util.Objects;

public class BankAccount {

    private String accountNumber;
    private String accountHolder;
    private double balance;

    private static int accountCounter = 0;

    // Constructor 1
    public BankAccount() {
        this("UNKNOWN");
    }

    // Constructor 2
    public BankAccount(String accountHolder) {
        this(accountHolder, 0.0);
    }

    // Constructor 3
    public BankAccount(String accountHolder, double balance) {

        if (accountHolder == null || accountHolder.isBlank()) {
            throw new IllegalArgumentException("Account holder cannot be empty");
        }

        if (balance < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative");
        }

        accountCounter++;

        this.accountNumber = "ACC" + accountCounter;
        this.accountHolder = accountHolder;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {

        if (amount <= 0) {
            throw new IllegalArgumentException(
                    "Deposit amount must be greater than zero"
            );
        }

        balance += amount;
    }

    public boolean withdraw(double amount) {

        if (amount <= 0) {
            return false;
        }

        if (amount > balance) {
            return false;
        }

        balance -= amount;
        return true;
    }

    public static int getAccountCount() {
        return accountCounter;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof BankAccount)) {
            return false;
        }

        BankAccount other = (BankAccount) obj;

        return Objects.equals(
                this.accountNumber,
                other.accountNumber
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNumber);
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "accountNumber='" + accountNumber + '\'' +
                ", accountHolder='" + accountHolder + '\'' +
                ", balance=" + balance +
                '}';
    }
}