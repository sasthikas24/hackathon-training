
package payment;

public class UPIPayment extends Payment implements Refundable {

    private final String upiId;

    public UPIPayment(String transactionId, double amount, String upiId) {
        super(transactionId, amount);

        if (upiId == null || !upiId.matches("[A-Za-z0-9._-]+@[A-Za-z0-9.-]+")) {
            throw new IllegalArgumentException("Invalid UPI ID");
        }

        this.upiId = upiId;
    }

    @Override
    public void pay() {
        System.out.println("Processing UPI payment for " + upiId);
        printReceipt();
    }

    @Override
    public boolean refund(double refundAmount) {
        if (!Double.isFinite(refundAmount)
                || refundAmount <= 0
                || refundAmount > amount) {
            return false;
        }

        amount -= refundAmount;
        System.out.println("UPI refund successful: " + refundAmount);
        return true;
    }
}