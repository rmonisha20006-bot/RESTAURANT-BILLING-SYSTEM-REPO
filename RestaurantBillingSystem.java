import java.util.*;

class MenuItem {
    String name;
    double price;

    MenuItem(String name, double price) {
        this.name = name;
        this.price = price;
    }
}

class OrderItem {
    MenuItem item;
    int quantity;

    OrderItem(MenuItem item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    double getTotal() {
        return item.price * quantity;
    }
}

public class RestaurantBillingSystem {

    static final double GST = 0.05;

    static ArrayList<MenuItem> menu = new ArrayList<>();
    static ArrayList<OrderItem> order = new ArrayList<>();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        initializeMenu();

        int choice;

        do {
            System.out.println("\n===== RESTAURANT BILLING SYSTEM =====");
            System.out.println("1. Show Menu");
            System.out.println("2. Add Item to Order");
            System.out.println("3. Remove Item from Order");
            System.out.println("4. Generate Bill");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    showMenu();
                    break;

                case 2:
                    addItem(sc);
                    break;

                case 3:
                    removeItem(sc);
                    break;

                case 4:
                    generateBill();
                    break;

                case 5:
                    System.out.println("Thank you! Visit Again.");
                    break;

                default:
                    System.out.println("Invalid Choice!");
            }

        } while (choice != 5);

        sc.close();
    }

    static void initializeMenu() {
        menu.add(new MenuItem("Pizza", 200));
        menu.add(new MenuItem("Burger", 120));
        menu.add(new MenuItem("Pasta", 150));
        menu.add(new MenuItem("Sandwich", 100));
        menu.add(new MenuItem("Coffee", 80));
    }

    static void showMenu() {
        System.out.println("\n----- MENU -----");

        for (int i = 0; i < menu.size(); i++) {
            System.out.println((i + 1) + ". " + menu.get(i).name + " - ₹" + menu.get(i).price);
        }
    }

    static void addItem(Scanner sc) {

        showMenu();

        System.out.print("Select item number: ");
        int itemNo = sc.nextInt();

        if (itemNo < 1 || itemNo > menu.size()) {
            System.out.println("Invalid item.");
            return;
        }

        System.out.print("Enter quantity: ");
        int qty = sc.nextInt();

        order.add(new OrderItem(menu.get(itemNo - 1), qty));

        System.out.println("Item added successfully.");
    }

    static void removeItem(Scanner sc) {

        if (order.isEmpty()) {
            System.out.println("No items in order.");
            return;
        }

        System.out.println("\nItems in Order:");
        for (int i = 0; i < order.size(); i++) {
            System.out.println((i + 1) + ". " + order.get(i).item.name);
        }

        System.out.print("Enter item number to remove: ");
        int itemNo = sc.nextInt();

        if (itemNo >= 1 && itemNo <= order.size()) {
            order.remove(itemNo - 1);
            System.out.println("Item removed.");
        } else {
            System.out.println("Invalid item.");
        }
    }

    static void generateBill() {

        if (order.isEmpty()) {
            System.out.println("No items ordered.");
            return;
        }

        double subtotal = 0;

        System.out.println("\n====== ITEMIZED BILL ======");
        System.out.printf("%-15s %-10s %-10s\n", "Item", "Qty", "Total");

        for (OrderItem oi : order) {

            double total = oi.getTotal();
            subtotal += total;

            System.out.printf("%-15s %-10d %-10.2f\n",
                    oi.item.name,
                    oi.quantity,
                    total);
        }

        double gstAmount = subtotal * GST;
        double finalTotal = subtotal + gstAmount;

        System.out.println("-------------------------------");
        System.out.println("Subtotal : ₹" + subtotal);
        System.out.println("GST (5%) : ₹" + gstAmount);
        System.out.println("Total Bill : ₹" + finalTotal);
    }
}