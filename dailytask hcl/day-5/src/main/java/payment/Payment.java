
package payment;

public abstract class Payment {

    private final String transactionId;
    protected double amount;

    public Payment(String transactionId, double amount) {
        if (transactionId == null || transactionId.isBlank()) {
            throw new IllegalArgumentException("Transaction ID cannot be empty");
        }

        if (!Double.isFinite(amount) || amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive and finite");
        }

        this.transactionId = transactionId;
        this.amount = amount;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public double getAmount() {
        return amount;
    }

    public abstract void pay();

    public void printReceipt() {
        System.out.println("Transaction ID: " + transactionId);
        System.out.println("Amount: " + amount);
    }
}