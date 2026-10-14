
import java.util.*;

public class EmployeeDirectory {

    static class Employee implements Comparable<Employee> {
        private final int id;
        private final String name;
        private final String department;
        private final double salary;

        Employee(int id, String name, String department, double salary) {
            this.id = id;
            this.name = name;
            this.department = department;
            this.salary = salary;
        }

        int getId() {
            return id;
        }

        String getName() {
            return name;
        }

        String getDepartment() {
            return department;
        }

        double getSalary() {
            return salary;
        }

        // Natural ordering: employee ID
        @Override
        public int compareTo(Employee other) {
            return Integer.compare(this.id, other.id);
        }

        // Employees are considered equal when their IDs match.
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof Employee)) return false;
            Employee other = (Employee) obj;
            return this.id == other.id;
        }

        @Override
        public int hashCode() {
            return Integer.hashCode(id);
        }

        @Override
        public String toString() {
            return "ID=" + id + ", Name=" + name
                    + ", Department=" + department
                    + ", Salary=" + salary;
        }
    }

    // Demonstrates the bug caused by missing hashCode().
    static class BrokenEmployee {
        private final int id;
        private final String name;

        BrokenEmployee(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof BrokenEmployee)) return false;
            BrokenEmployee other = (BrokenEmployee) obj;
            return this.id == other.id;
        }

        // Intentionally missing hashCode() for the bug demonstration.
    }

    public static void main(String[] args) {

        Employee[] employees = {
            new Employee(104, "Anu", "IT", 65000),
            new Employee(101, "Bala", "HR", 50000),
            new Employee(103, "Charan", "IT", 80000),
            new Employee(102, "Divya", "Finance", 75000),
            new Employee(105, "Ezhil", "HR", 60000),
            new Employee(106, "Farah", "Finance", 90000),
            new Employee(107, "Gokul", "IT", 80000)
        };

        // 1. HashMap lookup by employee ID
        Map<Integer, Employee> employeeMap = new HashMap<>();

        for (Employee employee : employees) {
            employeeMap.put(employee.getId(), employee);
        }

        System.out.println("=== EMPLOYEE LOOKUP ===");
        System.out.println(employeeMap.get(103));

        System.out.println("\n=== FREQUENCY COUNT ===");

        // Count employees in each department using merge().
        Map<String, Integer> departmentCounts = new HashMap<>();

        for (Employee employee : employees) {
            departmentCounts.merge(employee.getDepartment(), 1, Integer::sum);
        }

        System.out.println(departmentCounts);

        // 2. TreeMap department report
        Map<String, List<Employee>> departmentReport = new TreeMap<>();

        for (Employee employee : employees) {
            departmentReport
                    .computeIfAbsent(employee.getDepartment(), key -> new ArrayList<>())
                    .add(employee);
        }

        System.out.println("\n=== DEPARTMENT REPORT ===");

        for (Map.Entry<String, List<Employee>> entry : departmentReport.entrySet()) {
            System.out.println(entry.getKey() + ":");
            for (Employee employee : entry.getValue()) {
                System.out.println("  " + employee);
            }
        }

        // 3. Comparable: sort employees by ID
        List<Employee> sortedById = new ArrayList<>(employeeMap.values());
        Collections.sort(sortedById);

        System.out.println("\n=== SORTED BY EMPLOYEE ID ===");
        sortedById.forEach(System.out::println);

        // 4. Comparator: salary descending, then name ascending
        Comparator<Employee> salaryComparator =
                Comparator.comparingDouble(Employee::getSalary)
                        .reversed()
                        .thenComparing(Employee::getName);

        List<Employee> topFive = new ArrayList<>(employeeMap.values());
        topFive.sort(salaryComparator);

        System.out.println("\n=== TOP 5 SALARIES ===");
        topFive.stream()
                .limit(5)
                .forEach(System.out::println);

        // 5. Demonstrate missing hashCode() bug
        System.out.println("\n=== EQUALS/HASHCODE BUG DEMO ===");

        Map<BrokenEmployee, String> brokenMap = new HashMap<>();
        BrokenEmployee first = new BrokenEmployee(201, "Ravi");
        BrokenEmployee second = new BrokenEmployee(201, "Ravi");

        brokenMap.put(first, "Employee record");

        System.out.println("Objects equal: " + first.equals(second));
        System.out.println("Lookup with equal object: " + brokenMap.get(second));

        // Fix: implement both equals() and hashCode().
        Map<Employee, String> fixedMap = new HashMap<>();
        Employee fixedFirst = new Employee(201, "Ravi", "IT", 50000);
        Employee fixedSecond = new Employee(201, "Ravi", "IT", 50000);

        fixedMap.put(fixedFirst, "Employee record");

        System.out.println("Fixed objects equal: " + fixedFirst.equals(fixedSecond));
        System.out.println("Lookup after fix: " + fixedMap.get(fixedSecond));
    }
}
