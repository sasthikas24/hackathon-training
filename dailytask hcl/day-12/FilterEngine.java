
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class FilterEngine {

    record Product(String name, String category, double price, boolean inStock) {
        public Product() {
            this("Default Product", "General", 0.0, false);
        }
    }

    @FunctionalInterface
    interface ProductRule {
        boolean test(Product product);
    }

    // Static method reference
    static boolean isAffordable(Product product) {
        return product.price() <= 1000;
    }

    public static void main(String[] args) {

        List<Product> products = new ArrayList<>(List.of(
            new Product("Laptop", "Electronics", 75000, true),
            new Product("Mouse", "Electronics", 800, true),
            new Product("Chair", "Furniture", 2500, false),
            new Product("Notebook", "Stationery", 100, true),
            new Product("Pen", "Stationery", 50, false)
        ));

        // Predicate: filter products
        double maxPrice = 1000;
        String category = "Stationery";

        Predicate<Product> priceFilter =
            p -> p.price() <= maxPrice;

        Predicate<Product> categoryFilter =
            p -> p.category().equalsIgnoreCase(category);

        // Unbound instance method reference
        Predicate<Product> stockFilter = Product::inStock;

        // Compose predicates using and(), or(), negate()
        Predicate<Product> affordable =
            FilterEngine::isAffordable;

        Predicate<Product> finalFilter =
            priceFilter.and(categoryFilter).and(stockFilter);

        System.out.println("=== Filtered Products ===");
        products.stream()
            .filter(finalFilter)
            .forEach(System.out::println);

        System.out.println("\n=== Affordable OR In Stock ===");
        products.stream()
            .filter(affordable.or(stockFilter))
            .forEach(System.out::println);

        System.out.println("\n=== Out of Stock ===");
        products.stream()
            .filter(stockFilter.negate())
            .forEach(System.out::println);

        // Function: transform product data
        Function<Product, String> getName = Product::name;
        Function<String, String> uppercase = String::toUpperCase;
        Function<String, String> addLabel = "[PRODUCT] "::concat;

        Function<Product, String> productLabel =
            getName.andThen(uppercase).andThen(addLabel);

        System.out.println("\n=== Transformed Product Names ===");
        products.stream()
            .map(productLabel)
            .forEach(System.out::println);

        // compose(): apply uppercase before adding the label
        Function<String, String> formatName =
            addLabel.compose(uppercase);

        System.out.println("\n" + formatName.apply("laptop"));

        // Consumer: print products
        Consumer<Product> printProduct = System.out::println;
        Consumer<Product> printDetails =
            printProduct.andThen(p ->
                System.out.println("Category: " + p.category()));

        System.out.println("\n=== Product Details ===");
        products.stream()
            .filter(finalFilter)
            .forEach(printDetails);

        // Supplier: create default products
        Supplier<Product> defaultProduct = Product::new;

        System.out.println("\n=== Default Product ===");
        System.out.println(defaultProduct.get());

        // Bound instance method reference
        Consumer<String> printer = System.out::println;
        printer.accept("Bound method reference works!");

        // Custom functional interface with a lambda
        ProductRule premiumProduct =
            p -> p.price() > 50000;

        System.out.println("\n=== Premium Products ===");
        products.stream()
            .filter(premiumProduct::test)
            .forEach(System.out::println);

        // Effectively-final variable capture
        System.out.println("\nMaximum price captured: " + maxPrice);
    }
}
