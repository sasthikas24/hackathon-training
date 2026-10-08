package app;

import model.BankAccount;
import service.BankService;

public class BankApp {

    public static void main(String[] args) {

        BankAccount account = new BankAccount("Sasthika", 10000.00);

        BankService service = new BankService();

        System.out.println("=== ACCOUNT DETAILS ===");
        service.printAccountDetails(account);

        System.out.println("\n=== DEPOSIT ===");
        service.deposit(account, 2000.00);
        service.printAccountDetails(account);

        System.out.println("\n=== WITHDRAW ===");

        boolean successful = service.withdraw(account, 3000.00);

        if (successful) {
            System.out.println("Withdrawal successful.");
        } else {
            System.out.println("Withdrawal failed.");
        }

        service.printAccountDetails(account);

        System.out.println("\nTotal accounts created: "
                + BankAccount.getAccountCount());
    }
}