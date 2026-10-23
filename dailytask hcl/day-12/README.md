
# Day 12 – Functional Programming

## Objective
Implement a Product Filter Engine using Java functional programming.

## Concepts Covered
- Lambda expressions and effectively-final variable capture
- Predicate: and(), or(), negate()
- Function: andThen(), compose()
- Consumer: andThen()
- Supplier for default object creation
- Four method reference types
- Custom @FunctionalInterface
- Stream API for filtering and transforming products

## Compile and Run
```powershell
javac -Xlint:all -Werror -d "." FilterEngine.java
java FilterEngine
```

## Expected Outcome
Filter products by price, category and stock availability, transform product names, print product details, and create a default product using a Supplier.
