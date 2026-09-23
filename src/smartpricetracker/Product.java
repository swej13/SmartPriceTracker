package smartpricetracker;

import java.io.Serializable;
import java.util.ArrayList;

public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String name;
    private double previousPrice;
    private double currentPrice;
    private double targetPrice;

    private ArrayList<Double> priceHistory;

    public Product(int id, String name, double previousPrice,
                   double currentPrice, double targetPrice) {

        this.id = id;
        this.name = name;
        this.previousPrice = previousPrice;
        this.currentPrice = currentPrice;
        this.targetPrice = targetPrice;

        priceHistory = new ArrayList<>();

        priceHistory.add(previousPrice);
        priceHistory.add(currentPrice);
    }

    public int getId() {
        return id;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void updatePrice(double currentPrice, double targetPrice) {

        this.previousPrice = this.currentPrice;
        this.currentPrice = currentPrice;
        this.targetPrice = targetPrice;

        priceHistory.add(currentPrice);
    }

    public void displayPriceHistory() {

        System.out.println("\n===== PRICE HISTORY =====");

        for (int i = 0; i < priceHistory.size(); i++) {

            System.out.println(
                    "Price " + (i + 1) + ": ₹" + priceHistory.get(i)
            );
        }
    }

    public void checkDealAlert() {

        if (currentPrice <= targetPrice) {

            System.out.println("\n🔥 DEAL ALERT!");
            System.out.println("Product: " + name);
            System.out.println("Current Price: ₹" + currentPrice);
            System.out.println("Target Price: ₹" + targetPrice);
            System.out.println("Target price reached!");

        } else {

            double difference = currentPrice - targetPrice;

            System.out.println("\nNo deal yet.");
            System.out.println(
                    "₹" + difference +
                    " more needed to reach target price."
            );
        }
    }

    public void displayDetails() {

        double savings = previousPrice - currentPrice;

        double discountPercentage = 0;

        if (previousPrice > 0 && currentPrice < previousPrice) {

            discountPercentage =
                    (savings / previousPrice) * 100;
        }

        System.out.println("Product ID: " + id);
        System.out.println("Product Name: " + name);
        System.out.println("Previous Price: ₹" + previousPrice);
        System.out.println("Current Price: ₹" + currentPrice);
        System.out.println("Target Price: ₹" + targetPrice);

        if (currentPrice < previousPrice) {

            System.out.printf(
                    "You Save: ₹%.2f%n",
                    savings
            );

            System.out.printf(
                    "Discount: %.2f%%%n",
                    discountPercentage
            );

        } else {

            System.out.println("You Save: ₹0.00");
            System.out.println("Discount: 0.00%");
        }

        if (currentPrice <= targetPrice) {

            System.out.println("Status: TARGET REACHED!");

        } else if (currentPrice < previousPrice) {

            System.out.println("Status: PRICE DROPPED");

        } else if (currentPrice > previousPrice) {

            System.out.println("Status: PRICE INCREASED");

        } else {

            System.out.println("Status: PRICE UNCHANGED");
        }
    }
}