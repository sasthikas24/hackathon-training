
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SalesAnalytics {

    record Order(
        int orderId,
        String customer,
        String category,
        String status,
        double amount,
        List<String> products
    ) {}

    private static final List<String> CUSTOMERS =
        List.of("Anu", "Bala", "Charan", "Divya", "Esha");

    private static final List<String> CATEGORIES =
        List.of("Electronics", "Clothing", "Books", "Home");

    private static final List<String> STATUSES =
        List.of("COMPLETED", "PENDING", "CANCELLED");

    private static final List<String> PRODUCTS =
        List.of("Laptop", "Shirt", "Book", "Lamp", "Phone", "Bag");

    private static List<Order> generateOrders() {
        Random random = new Random(42);

        return IntStream.rangeClosed(1, 1000)
            .mapToObj(id -> {
                List<String> basket = random.ints(1, 4)
                    .limit(1)
                    .mapToObj(size -> IntStream.range(0, size)
                        .mapToObj(i -> PRODUCTS.get(
                            random.nextInt(PRODUCTS.size())))
                        .collect(Collectors.toList()))
                    .findFirst()
                    .orElseGet(ArrayList::new);

                return new Order(
                    id,
                    CUSTOMERS.get(random.nextInt(CUSTOMERS.size())),
                    CATEGORIES.get(random.nextInt(CATEGORIES.size())),
                    STATUSES.get(random.nextInt(STATUSES.size())),
                    100 + random.nextInt(9901),
                    basket
                );
            })
            .toList();
    }

    public static void main(String[] args) {
        List<Order> orders = generateOrders();

        System.out.println("Total orders: " + orders.stream().count());

        // 1. Revenue by category
        Map<String, Double> revenueByCategory = orders.stream()
            .filter(o -> !o.status().equals("CANCELLED"))
            .collect(Collectors.groupingBy(
                Order::category,
                Collectors.summingDouble(Order::amount)
            ));

        System.out.println("\n=== Revenue by Category ===");
        revenueByCategory.forEach(
            (category, revenue) ->
                System.out.printf("%s: %.2f%n", category, revenue)
        );

        // 2. Top three customers by total order value
        Map<String, Double> customerRevenue = orders.stream()
            .filter(o -> !o.status().equals("CANCELLED"))
            .collect(Collectors.groupingBy(
                Order::customer,
                Collectors.summingDouble(Order::amount)
            ));

        System.out.println("\n=== Top 3 Customers ===");
        customerRevenue.entrySet().stream()
            .sorted(Map.Entry.<String, Double>comparingByValue()
                .reversed())
            .limit(3)
            .forEach(entry -> System.out.printf(
                "%s: %.2f%n", entry.getKey(), entry.getValue()));

        // 3. Partition orders by completion status
        Map<Boolean, List<Order>> partitioned = orders.stream()
            .collect(Collectors.partitioningBy(
                o -> o.status().equals("COMPLETED")
            ));

        System.out.println("\n=== Completed vs Other Orders ===");
        System.out.println("Completed: " + partitioned.get(true).size());
        System.out.println("Other: " + partitioned.get(false).size());

        // 4. Average basket size
        double averageBasketSize = orders.stream()
            .collect(Collectors.averagingInt(
                o -> o.products().size()
            ));

        System.out.printf(
            "%nAverage basket size: %.2f products%n", averageBasketSize);

        // 5. Comma-joined product list
        String productList = orders.stream()
            .flatMap(o -> o.products().stream())
            .distinct()
            .sorted()
            .collect(Collectors.joining(", "));

        System.out.println("\n=== Unique Products ===");
        System.out.println(productList);

        // 6. Collector summary statistics
        System.out.println("\n=== Order Amount Summary ===");
        System.out.println(orders.stream()
            .collect(Collectors.summarizingDouble(Order::amount)));

        // 7. Intermediate operations: filter, map, distinct, sorted, limit
        System.out.println("\n=== Top 5 Completed Orders ===");
        orders.stream()
            .filter(o -> o.status().equals("COMPLETED"))
            .sorted(Comparator.comparingDouble(Order::amount).reversed())
            .limit(5)
            .map(o -> o.orderId() + " - " + o.customer()
                + " - " + o.amount())
            .forEach(System.out::println);

        // 8. Terminal operations: count, anyMatch, reduce
        boolean hasLargeOrder = orders.stream()
            .anyMatch(o -> o.amount() > 9000);

        double totalRevenue = orders.stream()
            .filter(o -> !o.status().equals("CANCELLED"))
            .map(Order::amount)
            .reduce(0.0, Double::sum);

        System.out.println("\nHas order above 9000: " + hasLargeOrder);
        System.out.printf("Total non-cancelled revenue: %.2f%n",
            totalRevenue);

        // 9. Sequential vs parallel CPU-heavy mapping
        List<Integer> numbers = IntStream.rangeClosed(1, 2000)
            .boxed()
            .toList();

        long sequentialStart = System.nanoTime();
        long sequentialResult = numbers.stream()
            .mapToLong(SalesAnalytics::cpuHeavyCalculation)
            .sum();
        long sequentialTime = System.nanoTime() - sequentialStart;

        long parallelStart = System.nanoTime();
        long parallelResult = numbers.parallelStream()
            .mapToLong(SalesAnalytics::cpuHeavyCalculation)
            .sum();
        long parallelTime = System.nanoTime() - parallelStart;

        System.out.println("\n=== Performance Comparison ===");
        System.out.println("Results equal: "
            + (sequentialResult == parallelResult));
        System.out.printf("Sequential: %.2f ms%n",
            sequentialTime / 1_000_000.0);
        System.out.printf("Parallel: %.2f ms%n",
            parallelTime / 1_000_000.0);
        System.out.println(
            "Timings vary by machine and system load.");
    }

    private static long cpuHeavyCalculation(int number) {
        long result = 0;
        for (int i = 1; i <= 3000; i++) {
            result += (long) number * i % 97;
        }
        return result;
    }
}
