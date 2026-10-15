# Day 9 - Generics

## Objective
Practice Java generics, bounded types, wildcards, and resource management.

## Concepts Covered
- Generic Pair<K, V> class.
- Bounded Box<T extends Number> class.
- Generic max() method using Comparable.
- Wildcards: ? extends Number and ? super Integer.
- Reading products from CSV using try-with-resources.
- AutoCloseable and reverse resource-closing order.
- Strict compilation without warnings.

## How to Run
```powershell
javac -Xlint:all -Werror GenericToolkit.java
java GenericToolkit
```

## Expected Results
- Generic Pair displays a key and value.
- Box displays a numeric value.
- max() returns the larger value.
- Wildcard methods calculate a sum and accept integers.
- Five products are read from products.csv.
- Resources close in reverse order.

## Files
- GenericToolkit.java
- products.csv
- README.md