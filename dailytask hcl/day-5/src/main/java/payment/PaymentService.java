
package payment;

public class PaymentService {

    // First overloaded method
    public void pay(Payment payment) {
        payment.pay();
    }

    // Second overloaded method
    public void pay(Payment payment, String note) {
        System.out.println("Payment note: " + note);
        payment.pay();
    }
}