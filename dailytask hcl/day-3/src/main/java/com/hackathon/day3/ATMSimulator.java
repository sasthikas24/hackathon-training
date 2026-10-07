package com.hackathon.day3;

import java.util.Scanner;

public class ATMSimulator {

    private static final int CORRECT_PIN = 1234;
    private static final int MAX_PIN_ATTEMPTS = 3;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        double balance = 10000.00;

        double[] miniStatement = {
            500.00,
            -1000.00,
            250.00,
            -750.00
        };

        boolean authenticated = false;

        // Maximum 3 PIN attempts
        for (int attempt = 1; attempt <= MAX_PIN_ATTEMPTS; attempt++) {

            System.out.print("Enter your 4-digit PIN: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter numbers only.");
                scanner.next();
                continue;
            }

            int pin = scanner.nextInt();

            if (pin == CORRECT_PIN) {
                authenticated = true;
                System.out.println("PIN accepted.");
                break;
            } else {
                System.out.println("Incorrect PIN. Attempts remaining: "
                        + (MAX_PIN_ATTEMPTS - attempt));
            }
        }

        if (!authenticated) {
            System.out.println("Too many incorrect attempts. Card blocked.");
            scanner.close();
            return;
        }

        int choice = 0;

        // ATM menu
        menuLoop:
        do {
            System.out.println("\n===== ATM MENU =====");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Mini Statement");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
                continue;
            }

            choice = scanner.nextInt();

            switch (choice) {

                case 1:
                    System.out.printf("Current Balance: ₹%.2f%n", balance);
                    break;

                case 2:
                    System.out.print("Enter deposit amount: ");

                    if (!scanner.hasNextDouble()) {
                        System.out.println("Invalid amount.");
                        scanner.next();
                        continue;
                    }

                    double deposit = scanner.nextDouble();

                    if (deposit <= 0) {
                        System.out.println("Deposit must be greater than zero.");
                        continue;
                    }

                    balance += deposit;
                    System.out.printf(
                            "Deposit successful. New Balance: ₹%.2f%n",
                            balance
                    );
                    break;

                case 3:
                    System.out.print("Enter withdrawal amount: ");

                    if (!scanner.hasNextDouble()) {
                        System.out.println("Invalid amount.");
                        scanner.next();
                        continue;
                    }

                    double withdrawal = scanner.nextDouble();

                    if (withdrawal <= 0) {
                        System.out.println("Withdrawal must be greater than zero.");
                        continue;
                    }

                    if (withdrawal > balance) {
                        System.out.println("Insufficient balance.");
                        continue;
                    }

                    balance -= withdrawal;

                    System.out.printf(
                            "Withdrawal successful. New Balance: ₹%.2f%n",
                            balance
                    );
                    break;

                case 4:
                    System.out.println("\n===== MINI STATEMENT =====");

                    for (double transaction : miniStatement) {
                        System.out.printf("Transaction: ₹%.2f%n", transaction);
                    }

                    break;

                case 5:
                    System.out.println("Thank you for using the ATM.");
                    break menuLoop;

                default:
                    System.out.println("Invalid choice. Please select 1-5.");
                    break;
            }

        } while (choice != 5);

        scanner.close();
    }
}