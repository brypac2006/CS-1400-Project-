import java.util.Scanner;

public class RestaurantCheckManager {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double totalSales = 0.0;
        double totalTips = 0.0;
        int numberOfChecks = 0;

        while (true) {
            System.out.println("Enter the sale amount:");
            double saleAmount = scanner.nextDouble();

            System.out.println("Enter the tip amount (if any, or 0):");
            double tipAmount = scanner.nextDouble();

            double totalAmount = saleAmount + tipAmount;

            // Handle edge cases
            if (totalAmount == 0) {
                totalAmount = saleAmount + tipAmount;
            } else if (tipAmount == 0) {
                tipAmount = totalAmount - saleAmount;
                if (tipAmount < 0) tipAmount = 0;
            } else if (totalAmount < saleAmount) {
                totalAmount = saleAmount;
                tipAmount = 0;
            }

            totalSales += saleAmount;
            totalTips += tipAmount;
            numberOfChecks++;

            System.out.print("Do you want to stop? (y/Y to quit): ");
            String input = scanner.next();
            if (input.equalsIgnoreCase("y")) break;
        }

        System.out.println("\nTotal Sale Amount: $" + totalSales);
        System.out.println("Total Tip Amount: $" + totalTips);
        

        // Call the method to divide the tips
        divideTips(totalTips, 2);  // Assuming 2 servers worked that day

        scanner.close();
    }

    // Method to divide the total tips among employees
    public static void divideTips(double totalTips, int numOfServers) {
        double serverShare = (totalTips * 0.60) / numOfServers;
        double kitchenShare = totalTips * 0.20;
        double hostShare = totalTips * 0.10;
        double busserShare = totalTips * 0.10;

        System.out.println("\nTip Distribution:");
        System.out.printf("Each server receives: $%.2f\n", serverShare);
        System.out.printf("Chef receives: $%.2f\n", kitchenShare * 0.50);
        System.out.printf("Sous-chef receives: $%.2f\n", kitchenShare * 0.30);
        System.out.printf("Kitchen aid receives: $%.2f\n", kitchenShare * 0.20);
        System.out.printf("Host/Hostess receives: $%.2f\n", hostShare);
        System.out.printf("Busser receives: $%.2f\n", busserShare);
    }
}







