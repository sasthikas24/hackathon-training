public class MonthlyUsageAnalyser {

    public static double calculateCost(long usage) {

        if (usage <= Constants.LOW_USAGE_LIMIT) {
            return usage * Constants.LOW_RATE;
        } else if (usage <= Constants.MEDIUM_USAGE_LIMIT) {
            return (Constants.LOW_USAGE_LIMIT * Constants.LOW_RATE)
                    + ((usage - Constants.LOW_USAGE_LIMIT) * Constants.MEDIUM_RATE);
        } else {
            return (Constants.LOW_USAGE_LIMIT * Constants.LOW_RATE)
                    + ((Constants.MEDIUM_USAGE_LIMIT - Constants.LOW_USAGE_LIMIT) * Constants.MEDIUM_RATE)
                    + ((usage - Constants.MEDIUM_USAGE_LIMIT) * Constants.HIGH_RATE);
        }
    }

    public static void main(String[] args) {

        int[] monthlyUsage = {
            1200, 1500, 1800, 2100,
            2300, 2500, 2700, 2400,
            2200, 1900, 1600, 1300
        };

        long total = 0;
        int maximum = monthlyUsage[0];
        int minimum = monthlyUsage[0];

        for (int usage : monthlyUsage) {
            total += usage;

            if (usage > maximum) {
                maximum = usage;
            }

            if (usage < minimum) {
                minimum = usage;
            }
        }

        double average = (double) total / Constants.MONTHS;
        double totalCost = calculateCost(total);
        char grade = average >= Constants.MEDIUM_USAGE_LIMIT
                ? 'A'
                : average >= Constants.LOW_USAGE_LIMIT
                ? 'B'
                : 'C';

        System.out.println("Monthly Usage Analyser");
        System.out.println("----------------------");
        System.out.println("Total   : " + total);
        System.out.println("Average : " + average);
        System.out.println("Maximum : " + maximum);
        System.out.println("Minimum : " + minimum);
        System.out.println("Grade   : " + grade);
        System.out.println("Cost    : " + totalCost);

        int[][] houseUsage = {
            {1200, 1500, 1800},
            {2000, 2200, 2100},
            {1700, 1900, 2300}
        };

        System.out.println("\nUsage for 3 Houses:");

        for (int house = 0; house < houseUsage.length; house++) {
            System.out.print("House " + (house + 1) + ": ");

            for (int month = 0; month < houseUsage[house].length; month++) {
                System.out.print(houseUsage[house][month] + " ");
            }

            System.out.println();
        }
    }
}