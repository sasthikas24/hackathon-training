
package orders;

import java.io.IOException;

public class OrderProcessor {

    private int availableStock = 10;

    public void processOrder(int quantity)
            throws InsufficientStockException {

        try {
            if (quantity <= 0) {
                throw new InvalidQuantityException(
                        "Quantity must be greater than zero."
                );
            }

            if (quantity > availableStock) {
                IOException cause = new IOException(
                        "Inventory database confirms only "
                                + availableStock + " items available."
                );

                throw new InsufficientStockException(
                        "Insufficient stock for quantity: " + quantity,
                        cause
                );
            }

            availableStock -= quantity;
            System.out.println(
                    "Order successful! Quantity: " + quantity
            );

        } catch (InsufficientStockException
                 | InvalidQuantityException exception) {

            System.out.println("Order failed: " + exception.getMessage());

            if (exception.getCause() != null) {
                System.out.println(
                        "Root cause: " + exception.getCause().getMessage()
                );
            }

            throw exception;

        } finally {
            System.out.println(
                    "AUDIT: Order processing attempt completed."
            );
        }
    }

    public static void main(String[] args) {
        OrderProcessor processor = new OrderProcessor();
        //int[] orders = {0};
        int[] orders = {3, 0, 15, 2};
       // int[] orders = {15};
        for (int quantity : orders) {
            try {
                processor.processOrder(quantity);
            } catch (InsufficientStockException
                     | InvalidQuantityException exception) {
                System.out.println(
                        "Handled in main: " + exception.getClass().getSimpleName()
                );
                
            }

            System.out.println("--------------------");
        }
    }
}
