
package payment;

public class CashPayment extends Payment {

    public CashPayment(String transactionId, double amount) {
        super(transactionId, amount);
    }

    @Override
    public void pay() {
        System.out.println("Processing cash payment");
        printReceipt();
    }
}