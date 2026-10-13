
package library;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class LibraryWaitlist {

    public static void main(String[] args) {
        // ArrayList preserves catalogue order and supports fast indexed access.
        List<String> catalogue = new ArrayList<>();
        catalogue.add("Java Fundamentals");
        catalogue.add("Data Structures");
        catalogue.add("Clean Code");
        catalogue.add("Java Fundamentals");

        System.out.println("LIBRARY CATALOGUE");
        for (String book : catalogue) {
            System.out.println(book);
        }

        // LinkedHashSet removes duplicates while preserving insertion order.
        Set<String> uniqueBooks = new LinkedHashSet<>(catalogue);
        System.out.println("\nUNIQUE BOOKS");
        for (String book : uniqueBooks) {
            System.out.println(book);
        }

        // ArrayDeque is efficient for a first-in, first-out waitlist.
        Queue<String> waitlist = new ArrayDeque<>();
        waitlist.offer("Anu");
        waitlist.offer("Bala");
        waitlist.offer("Charan");

        System.out.println("\nWAITLIST");
        System.out.println("Next reader: " + waitlist.peek());
        System.out.println("Served reader: " + waitlist.poll());
        System.out.println("Remaining readers: " + waitlist);

        // PriorityQueue serves faculty requests before other requests.
        // Lower priority numbers are served first.
        PriorityQueue<Request> requests = new PriorityQueue<>(
                Comparator.comparingInt(Request::getPriority)
        );

        requests.offer(new Request("Student Ravi", 2));
        requests.offer(new Request("Faculty Meena", 1));
        requests.offer(new Request("Student Priya", 2));

        System.out.println("\nPRIORITY REQUESTS");
        while (!requests.isEmpty()) {
            System.out.println(requests.poll());
        }

        // Iterator.remove safely removes overdue catalogue items.
        List<String> overdueBooks = new ArrayList<>(catalogue);
        Iterator<String> iterator = overdueBooks.iterator();

        while (iterator.hasNext()) {
            String book = iterator.next();
            if (book.equals("Clean Code")) {
                iterator.remove();
            }
        }

        System.out.println("\nAFTER REMOVING OVERDUE BOOK");
        System.out.println(overdueBooks);

        // Compare ArrayList and LinkedList operations.
        // Results vary by computer and JVM; this is a simple benchmark.
        benchmarkCollections();
    }

    private static void benchmarkCollections() {
        final int size = 20000;
        final int repetitions = 5000;

        List<Integer> arrayList = new ArrayList<>();
        List<Integer> linkedList = new LinkedList<>();

        for (int i = 0; i < size; i++) {
            arrayList.add(i);
            linkedList.add(i);
        }

        long start = System.nanoTime();
        for (int i = 0; i < repetitions; i++) {
            arrayList.get(size / 2);
        }
        long arrayGetTime = System.nanoTime() - start;

        start = System.nanoTime();
        for (int i = 0; i < repetitions; i++) {
            linkedList.get(size / 2);
        }
        long linkedGetTime = System.nanoTime() - start;

        List<Integer> arrayInsertList = new ArrayList<>(arrayList);
        start = System.nanoTime();
        for (int i = 0; i < repetitions; i++) {
            arrayInsertList.add(0, -i);
        }
        long arrayInsertTime = System.nanoTime() - start;

        List<Integer> linkedInsertList = new LinkedList<>(linkedList);
        start = System.nanoTime();
        for (int i = 0; i < repetitions; i++) {
            linkedInsertList.add(0, -i);
        }
        long linkedInsertTime = System.nanoTime() - start;

        System.out.println("\nTIMING TABLE");
        System.out.printf("%-15s %-18s %-18s%n",
                "Collection", "get(mid) total", "insert at start");
        System.out.printf("%-15s %-18d %-18d%n",
                "ArrayList", arrayGetTime, arrayInsertTime);
        System.out.printf("%-15s %-18d %-18d%n",
                "LinkedList", linkedGetTime, linkedInsertTime);
        System.out.println("Times are in nanoseconds; results vary by run.");
    }

    private static class Request {
        private final String reader;
        private final int priority;

        Request(String reader, int priority) {
            this.reader = reader;
            this.priority = priority;
        }

        int getPriority() {
            return priority;
        }

        @Override
        public String toString() {
            return reader + " (priority " + priority + ")";
        }
    }
}
