import java.util.Scanner;

public class RestaurantCheckManager {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double totalSales = 0.0;
        double totalTips = 0.0;
        double completeTotal = 0.0;
        int checkCount = 0;

        while (true) {
            System.out.printf("Total sale amount: ");
            double saleAmount = scanner.nextDouble();

            System.out.print("Tip amount (or 0 if not provided): ");
            double tipAmount = scanner.nextDouble();

            System.out.print("Total amount (or 0 if not provided): ");
            double totalAmount = scanner.nextDouble();

            // Handle missing values : Edge Cases
            if (totalAmount == 0) {
                totalAmount = saleAmount + tipAmount; // Calculate total when not provided
            }
            if (tipAmount == 0) {
                tipAmount = totalAmount - saleAmount; // Calculate tip when not provided
                if (tipAmount < 0) {
                    tipAmount = 0;  // Correct negative tip
                }
            }
            if (totalAmount < saleAmount) {
                totalAmount = saleAmount;  // Ensure total is at least sale amount
                tipAmount = 0;
            }

            // Update cumulative totalsn
            totalSales += saleAmount;
            totalTips += tipAmount;
            completeTotal = totalSales + totalTips;
            checkCount++;

            // Display cumulative results after each check
            System.out.println("\nCheck count: " + checkCount);
            System.out.printf("Total sale so far: %.2f\n", totalSales);
            System.out.printf("Total pooled tip so far: %.2f\n", totalTips);
            System.out.printf("Total complete amount: %.2f\n", completeTotal);

            // Prompt to stop or continue
            System.out.print("Do you want to stop (y/n): ");
            String input = scanner.next();
            if (input.equalsIgnoreCase("y")) break;
            System.out.println(); //new line for more readability
        }

        // Final results and tip division
        System.out.printf("\nThe total sale amount is: %.2f\n", totalSales);
        System.out.printf("The total pooled tip amount is: %.2f\n", totalTips);

        divideTips(totalTips);

        scanner.close();
    }

    // Method to divide pooled tips among employees
    public static void divideTips(double totalTips) {
        // Distribution percentages
        double serversShare = 0.50 * totalTips;  // 50% for servers
        double kitchenShare = 0.30 * totalTips;  // 30% for kitchen staff
        double hostShare = 0.05 * totalTips;     // 5% for host/hostess
        double busserShare = 0.05 * totalTips;   // 5% for busser

        // Kitchen breakdown
        double chefShare = 0.50 * kitchenShare;       // 50% of kitchen share to chef
        double sousChefShare = 0.30 * kitchenShare;   // 30% to sous-chef
        double kitchenAidShare = 0.20 * kitchenShare; // 20% to kitchen aid

        // Divide servers' share equally among 2 working servers
        double serverTipPerPerson = serversShare / 2;

        System.out.println("\nTip Distribution:");
        System.out.printf("Each server gets: %.2f\n", serverTipPerPerson);
        System.out.printf("Chef gets: %.2f\n", chefShare);
        System.out.printf("Sous-chef gets: %.2f\n", sousChefShare);
        System.out.printf("Kitchen aid gets: %.2f\n", kitchenAidShare);
        System.out.printf("Host/Hostess gets: %.2f\n", hostShare);
        System.out.printf("Busser gets: %.2f\n", busserShare);
    }
}
