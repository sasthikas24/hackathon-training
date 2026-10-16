
import java.util.ArrayList;
import java.util.List;

public class MemoryLab {

    private static long calls;

    public static void main(String[] args) throws InterruptedException {
        if (args.length == 0) {
            System.out.println("Choose an experiment:");
            System.out.println("stack   - StackOverflowError");
            System.out.println("heap    - OutOfMemoryError");
            System.out.println("string  - String vs StringBuilder");
            System.out.println("gc      - Garbage collection");
            System.out.println("integer - Integer cache");
            return;
        }

        switch (args[0].toLowerCase()) {
            case "stack" -> stackExperiment();
            case "heap" -> heapExperiment();
            case "string" -> stringExperiment();
            case "gc" -> gcExperiment();
            case "integer" -> integerExperiment();
            default -> System.out.println("Unknown experiment: " + args[0]);
        }
    }

    // Experiment 1: StackOverflowError
    private static void recursiveCall() {
        calls++;
        recursiveCall();
    }

    private static void stackExperiment() {
        calls = 0;

        try {
            recursiveCall();
        } catch (StackOverflowError error) {
            System.out.println("Error: " + error);
            System.out.println("Recursive calls: " + calls);
        }
    }

    // Experiment 2: Heap exhaustion
    private static void heapExperiment() {
        List<byte[]> memory = new ArrayList<>();
        long totalAllocated = 0;

        try {
            while (true) {
                byte[] block = new byte[1024 * 1024];
                memory.add(block);
                totalAllocated += block.length;

                System.out.println("Retained: "
                        + totalAllocated / (1024 * 1024) + " MiB");
            }
        } catch (OutOfMemoryError error) {
            System.out.println("Error: " + error);
            System.out.println("Byte-array payload retained: "
                    + totalAllocated / (1024 * 1024) + " MiB");
        }
    }

    // Experiment 3: String concatenation performance
    private static void stringExperiment() {
        int iterations = 20_000;
        long start = System.nanoTime();
        String result = "";

        for (int i = 0; i < iterations; i++) {
            result += i;
        }

        long stringTime = System.nanoTime() - start;

        start = System.nanoTime();
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < iterations; i++) {
            builder.append(i);
        }

        String builderResult = builder.toString();
        long builderTime = System.nanoTime() - start;

        System.out.printf("String += time: %.3f ms%n",
                stringTime / 1_000_000.0);
        System.out.printf("StringBuilder time: %.3f ms%n",
                builderTime / 1_000_000.0);
        System.out.println("Both results match: "
                + result.equals(builderResult));
        System.out.println("Result length: " + result.length());
    }

    // Experiment 4: Garbage collection
    private static void gcExperiment() throws InterruptedException {
        for (int round = 1; round <= 8; round++) {
            for (int i = 0; i < 10; i++) {
                byte[] temporary = new byte[1024 * 1024];
                temporary[0] = 1;
            }

            System.out.println("Completed round " + round);

            if (round % 2 == 0) {
                System.gc();
                Thread.sleep(200);
            }
        }

        System.out.println("GC experiment finished");
    }

    // Experiment 5: Integer cache
    private static void integerExperiment() {
        Integer a = 100;
        Integer b = 100;
        Integer c = 200;
        Integer d = 200;

        System.out.println("a == b: " + (a == b));
        System.out.println("a.equals(b): " + a.equals(b));
        System.out.println("c == d: " + (c == d));
        System.out.println("c.equals(d): " + c.equals(d));
    }
}
