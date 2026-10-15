
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GenericToolkit {

    // 1. Generic Pair<K, V>
    static class Pair<K, V> {
        private final K key;
        private final V value;

        Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        K getKey() {
            return key;
        }

        V getValue() {
            return value;
        }

        @Override
        public String toString() {
            return "(" + key + ", " + value + ")";
        }
    }

    // 2. Bounded generic class
    static class Box<T extends Number> {
        private final T value;

        Box(T value) {
            this.value = value;
        }

        double doubleValue() {
            return value.doubleValue();
        }
    }

    // 3. Bounded generic method
    static <T extends Comparable<T>> T max(T first, T second) {
        return first.compareTo(second) >= 0 ? first : second;
    }

    // 4. ? extends: read values from a producer
    static double sumNumbers(List<? extends Number> numbers) {
        double sum = 0;

        for (Number number : numbers) {
            sum += number.doubleValue();
        }

        return sum;
    }

    // 5. ? super: add Integer values to a consumer
    static void addIntegers(List<? super Integer> destination) {
        destination.add(10);
        destination.add(20);
        destination.add(30);
    }

    // 6. Product model
    static class Product {
        private final int id;
        private final String name;
        private final double price;

        Product(int id, String name, double price) {
            this.id = id;
            this.name = name;
            this.price = price;
        }

        @Override
        public String toString() {
            return "Product{id=" + id
                    + ", name='" + name
                    + "', price=" + price + "}";
        }
    }

    // 7. AutoCloseable resource demonstration
    static class DemoResource implements AutoCloseable {
        private final String name;

        DemoResource(String name) {
            this.name = name;
            System.out.println(name + " opened");
        }

        void use() {
            System.out.println(name + " is in use");
        }

        @Override
        public void close() {
            System.out.println(name + " closed");
        }
    }

    public static void main(String[] args) throws IOException {

        System.out.println("=== GENERIC PAIR ===");
        Pair<Integer, String> pair = new Pair<>(101, "Laptop");
        System.out.println(pair);
        System.out.println("Key: " + pair.getKey());
        System.out.println("Value: " + pair.getValue());

        System.out.println("\n=== NUMBER BOX ===");
        Box<Integer> integerBox = new Box<>(250);
        System.out.println("Value: " + integerBox.doubleValue());

        System.out.println("\n=== MAXIMUM ===");
        System.out.println("Maximum integer: " + max(25, 40));
        System.out.println("Maximum string: " + max("Apple", "Orange"));

        System.out.println("\n=== WILDCARD EXTENDS ===");
        List<Integer> numbers = Arrays.asList(10, 20, 30, 40);
        System.out.println("Sum: " + sumNumbers(numbers));

        System.out.println("\n=== WILDCARD SUPER ===");
        List<Number> destination = new ArrayList<>();
        addIntegers(destination);
        System.out.println("Values: " + destination);

        System.out.println("\n=== READ PRODUCTS CSV ===");
        Path csvPath = Path.of("products.csv");

        try (BufferedReader reader = Files.newBufferedReader(csvPath)) {
            String line;
            boolean header = true;

            while ((line = reader.readLine()) != null) {
                if (header) {
                    header = false;
                    continue;
                }

                String[] fields = line.split(",", -1);

                if (fields.length != 3) {
                    throw new IOException("Invalid CSV row: " + line);
                }

                int id = Integer.parseInt(fields[0].trim());
                String name = fields[1].trim();
                double price = Double.parseDouble(fields[2].trim());

                Product product = new Product(id, name, price);
                System.out.println(product);
            }
        }

        System.out.println("\n=== RESOURCE CLOSE ORDER ===");

        try (DemoResource first = new DemoResource("Resource A");
             DemoResource second = new DemoResource("Resource B")) {
            first.use();
            second.use();
            System.out.println("Inside try block");
        }
    }
}
