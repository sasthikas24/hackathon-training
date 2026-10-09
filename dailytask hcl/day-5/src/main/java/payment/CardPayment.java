
package payment;

public class CardPayment extends Payment implements Refundable {

    private final String lastFourDigits;

    public CardPayment(String transactionId, double amount, String lastFourDigits) {
        super(transactionId, amount);

        if (lastFourDigits == null || !lastFourDigits.matches("\\d{4}")) {
            throw new IllegalArgumentException("Enter exactly four digits");
        }

        this.lastFourDigits = lastFourDigits;
    }

    @Override
    public void pay() {
        System.out.println("Processing card payment ending in " + lastFourDigits);
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
        System.out.println("Card refund successful: " + refundAmount);
        return true;
    }
}