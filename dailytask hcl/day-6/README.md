# Day 6: Exception Handling and AI-Assisted Debugging

## Order Processor

### Features
- Custom checked exception: `InsufficientStockException`
- Custom unchecked exception: `InvalidQuantityException`
- `throw` and `throws`
- Multi-catch exception handling
- Exception chaining using `getCause()`
- `finally` block for order auditing

### Test Results
- Quantity 3: Successful
- Quantity 0: Rejected because quantity must be positive
- Quantity 15: Rejected because stock is insufficient
- Quantity 2: Successful
- Audit message printed for every attempt

## AI Stack-Trace Analysis

### Stack Trace 1: Invalid Quantity

```text
Exception in thread "main" orders.InvalidQuantityException:
Quantity must be greater than zero.
    at orders.OrderProcessor.processOrder(OrderProcessor.java:14)
    at orders.OrderProcessor.main(OrderProcessor.java:55)
```

**AI explanation:** The program throws an unchecked exception when the requested quantity is zero or negative. The trace points to `processOrder()`, called from `main()`.

**Verification:** Correct. `InvalidQuantityException` extends `RuntimeException`, so it is unchecked. Line numbers may differ depending on the final source file.

### Stack Trace 2: Insufficient Stock

```text
Exception in thread "main" orders.InsufficientStockException:
Insufficient stock for quantity: 15
    at orders.OrderProcessor.processOrder(OrderProcessor.java:25)
    at orders.OrderProcessor.main(OrderProcessor.java:55)
Caused by: java.io.IOException:
Inventory database confirms only 7 items available.
    at orders.OrderProcessor.processOrder(OrderProcessor.java:20)
    ... 1 more
```
**AI Explanation:**
InvalidQuantityException is thrown in OrderProcessor.java line 15 when processOrder detects that quantity <= 0. The message in the trace is the message passed to its constructor.

The call sequence, read from the bottom of the stack trace upward, is:

main calls processor.processOrder(quantity) at line 64.
processOrder sees that the quantity is not positive and throws the exception at line 15.
The exception propagates back to main, where it is caught and printed with exception.printStackTrace().
It is unchecked: InvalidQuantityException.java extends RuntimeException. You can verify the explanation by checking those two lines in OrderProcessor.java and the superclass declaration in InvalidQuantityException.java.


**Verification:**
The top-level, or original, exception is orders.InsufficientStockException. It is thrown in OrderProcessor.processOrder at line 26 when the requested quantity exceeds available stock.

Its underlying cause is an IOException. The code creates it at OrderProcessor.java line 21 with the inventory message, then passes it as the cause when constructing the InsufficientStockException. This preserves the lower-level reason alongside the higher-level order failure.

Java prints ... 1 more because the cause’s stack trace shares one frame with the outer exception’s trace: the call from main at line 64. Java omits that repeated frame; it is still part of the cause’s stack trace.

InsufficientStockException is checked: InsufficientStockException.java declares it as extending Exception, not RuntimeException. You can verify this by checking the cause construction and outer throw in OrderProcessor.java, and the superclass declaration in InsufficientStockException.java.

## Important Note

The two stack traces above are illustrative examples for analysis. They are not captured directly from the current program output. The program currently catches both exceptions and prints friendly error messages rather than uncaught stack traces.

To verify AI-generated explanations, compare them with the source code, exception types, and actual Java stack traces.

## Conclusion

The Order Processor validates quantities, checks stock, preserves exception causes, and audits every order attempt without empty catch blocks.

