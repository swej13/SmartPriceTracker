package smartpricetracker;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Scanner;

public class SmartPriceTracker {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ArrayList<Product> products = new ArrayList<>();

        loadProducts(products);

        int choice = 0;

        do {

            try {

                System.out.println("\n===== SMART PRICE TRACKER =====");
                System.out.println("1. Add Product");
                System.out.println("2. View All Products");
                System.out.println("3. Search Product");
                System.out.println("4. Sort by Price");
                System.out.println("5. Delete Product");
                System.out.println("6. Update Product");
                System.out.println("7. View Price History");
                System.out.println("8. Check Deal Alert");
                System.out.println("9. Exit");
                System.out.println("10. Save Products");

                System.out.print("Enter your choice: ");
                choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {

                    case 1:

                        System.out.print("Enter Product ID: ");
                        int id = sc.nextInt();
                        sc.nextLine();

                        boolean duplicateId = false;

                        for (Product p : products) {

                            if (p.getId() == id) {
                                duplicateId = true;
                                break;
                            }
                        }

                        if (duplicateId) {

                            System.out.println(
                                    "Product ID already exists."
                            );

                            break;
                        }

                        System.out.print("Enter Product Name: ");
                        String name = sc.nextLine();

                        System.out.print("Enter Previous Price: ");
                        double previousPrice = sc.nextDouble();

                        System.out.print("Enter Current Price: ");
                        double currentPrice = sc.nextDouble();

                        System.out.print("Enter Target Price: ");
                        double targetPrice = sc.nextDouble();

                        if (previousPrice < 0 ||
                            currentPrice < 0 ||
                            targetPrice < 0) {

                            throw new IllegalArgumentException(
                                    "Price cannot be negative."
                            );
                        }

                        Product product = new Product(
                                id,
                                name,
                                previousPrice,
                                currentPrice,
                                targetPrice
                        );

                        products.add(product);

                        System.out.println(
                                "Product added successfully!"
                        );

                        break;

                    case 2:

                        if (products.isEmpty()) {

                            System.out.println(
                                    "No products available."
                            );

                        } else {

                            System.out.println(
                                    "\n===== ALL PRODUCTS ====="
                            );

                            for (Product p : products) {

                                System.out.println();
                                p.displayDetails();
                            }
                        }

                        break;

                    case 3:

                        System.out.print(
                                "Enter Product ID to search: "
                        );

                        int searchId = sc.nextInt();

                        boolean found = false;

                        for (Product p : products) {

                            if (p.getId() == searchId) {

                                p.displayDetails();

                                found = true;

                                break;
                            }
                        }

                        if (!found) {

                            System.out.println(
                                    "Product not found."
                            );
                        }

                        break;

                    case 4:

                        if (products.isEmpty()) {

                            System.out.println(
                                    "No products available."
                            );

                        } else {

                            Collections.sort(
                                    products,
                                    new Comparator<Product>() {

                                        public int compare(
                                                Product p1,
                                                Product p2) {

                                            return Double.compare(
                                                    p1.getCurrentPrice(),
                                                    p2.getCurrentPrice()
                                            );
                                        }
                                    }
                            );

                            System.out.println(
                                    "Products sorted by price."
                            );

                            for (Product p : products) {

                                System.out.println(
                                        p.getId() + " - " +
                                        p.getName() + " - ₹" +
                                        p.getCurrentPrice()
                                );
                            }
                        }

                        break;

                    case 5:

                        System.out.print(
                                "Enter Product ID to delete: "
                        );

                        int deleteId = sc.nextInt();

                        boolean deleted = false;

                        for (int i = 0;
                             i < products.size();
                             i++) {

                            if (products.get(i).getId()
                                    == deleteId) {

                                products.remove(i);

                                deleted = true;

                                System.out.println(
                                        "Product deleted successfully!"
                                );

                                break;
                            }
                        }

                        if (!deleted) {

                            System.out.println(
                                    "Product not found."
                            );
                        }

                        break;

                    case 6:

                        System.out.print(
                                "Enter Product ID to update: "
                        );

                        int updateId = sc.nextInt();

                        boolean updated = false;

                        for (Product p : products) {

                            if (p.getId() == updateId) {

                                System.out.print(
                                        "Enter New Current Price: "
                                );

                                double newCurrentPrice =
                                        sc.nextDouble();

                                System.out.print(
                                        "Enter New Target Price: "
                                );

                                double newTargetPrice =
                                        sc.nextDouble();

                                if (newCurrentPrice < 0 ||
                                    newTargetPrice < 0) {

                                    throw new IllegalArgumentException(
                                            "Price cannot be negative."
                                    );
                                }

                                p.updatePrice(
                                        newCurrentPrice,
                                        newTargetPrice
                                );

                                updated = true;

                                System.out.println(
                                        "Product updated successfully!"
                                );

                                break;
                            }
                        }

                        if (!updated) {

                            System.out.println(
                                    "Product not found."
                            );
                        }

                        break;

                    case 7:

                        System.out.print(
                                "Enter Product ID: "
                        );

                        int historyId = sc.nextInt();

                        boolean historyFound = false;

                        for (Product p : products) {

                            if (p.getId() == historyId) {

                                p.displayPriceHistory();

                                historyFound = true;

                                break;
                            }
                        }

                        if (!historyFound) {

                            System.out.println(
                                    "Product not found."
                            );
                        }

                        break;

                    case 8:

                        System.out.print(
                                "Enter Product ID: "
                        );

                        int alertId = sc.nextInt();

                        boolean alertFound = false;

                        for (Product p : products) {

                            if (p.getId() == alertId) {

                                p.checkDealAlert();

                                alertFound = true;

                                break;
                            }
                        }

                        if (!alertFound) {

                            System.out.println(
                                    "Product not found."
                            );
                        }

                        break;

                    case 9:

                        saveProducts(products);

                        System.out.println(
                                "Thank you for using Smart Price Tracker!"
                        );

                        break;

                    case 10:

                        saveProducts(products);

                        break;

                    default:

                        System.out.println(
                                "Invalid choice. Please select 1-10."
                        );
                }

            } catch (InputMismatchException e) {

                System.out.println(
                        "Invalid input! Please enter a number."
                );

                sc.nextLine();

            } catch (IllegalArgumentException e) {

                System.out.println(
                        "Error: " + e.getMessage()
                );
            }

        } while (choice != 9);

        sc.close();
    }

    public static void saveProducts(
            ArrayList<Product> products) {

        try {

            FileWriter fw =
                    new FileWriter("products.txt");

            PrintWriter pw =
                    new PrintWriter(fw);

            for (Product p : products) {

                pw.println(
                        p.getId() + "," +
                        p.getName() + "," +
                        p.getPreviousPrice() + "," +
                        p.getCurrentPrice() + "," +
                        p.getTargetPrice()
                );
            }

            pw.close();

            System.out.println(
                    "Products saved successfully!"
            );

        } catch (IOException e) {

            System.out.println(
                    "Error while saving products."
            );
        }
    }

    public static void loadProducts(
            ArrayList<Product> products) {

        try {

            FileReader fr =
                    new FileReader("products.txt");

            BufferedReader br =
                    new BufferedReader(fr);

            String line;

            while ((line = br.readLine()) != null) {

                String[] data = line.split(",");

                if (data.length != 5) {
                    continue;
                }

                int id =
                        Integer.parseInt(data[0]);

                String name =
                        data[1];

                double previousPrice =
                        Double.parseDouble(data[2]);

                double currentPrice =
                        Double.parseDouble(data[3]);

                double targetPrice =
                        Double.parseDouble(data[4]);

                Product product =
                        new Product(
                                id,
                                name,
                                previousPrice,
                                currentPrice,
                                targetPrice
                        );

                products.add(product);
            }

            br.close();

            System.out.println(
                    "Products loaded successfully!"
            );

        } catch (IOException e) {

            System.out.println(
                    "No previous data found."
            );

        } catch (NumberFormatException e) {

            System.out.println(
                    "Invalid data found in products.txt."
            );
        }
    }
}