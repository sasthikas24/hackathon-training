
# Day 13 – Streams API

## Objective
Analyze 1,000 sales orders using Java Streams and Collectors.

## Concepts Covered
- Stream creation and lazy intermediate operations
- filter, map, flatMap, sorted, distinct and limit
- count, anyMatch, reduce and collect
- Collectors.groupingBy and summingDouble
- Collectors.partitioningBy, averagingInt, joining and summarizingDouble
- Method references and comparator-based sorting
- Sequential and parallel stream performance

## Compile and Run
```powershell
javac -Xlint:all -Werror -d "." SalesAnalytics.java
java SalesAnalytics
```

## Expected Outcome
Generate sales orders, calculate category revenue, rank customers, partition order statuses, calculate basket size, summarize order amounts, and compare sequential and parallel processing.
