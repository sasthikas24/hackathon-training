
package payment;

public class PaymentApp {

    public static void main(String[] args) {

        PaymentService service = new PaymentService();

        Payment card = new CardPayment("TXN101", 2500.00, "1234");
        Payment upi = new UPIPayment("TXN102", 1500.00, "sasthika@upi");
        Payment cash = new CashPayment("TXN103", 500.00);

        System.out.println("=== CARD PAYMENT ===");
        service.pay(card);

        System.out.println("\n=== UPI PAYMENT ===");
        service.pay(upi, "Monthly bill");

        System.out.println("\n=== CASH PAYMENT ===");
        service.pay(cash);

        System.out.println("\n=== CARD REFUND ===");
        Refundable refundable = (Refundable) card;
        boolean refunded = refundable.refund(500.00);
        System.out.println("Refund successful: " + refunded);
        System.out.println("Remaining card amount: " + card.getAmount());
    }
}