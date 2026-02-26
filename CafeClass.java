package olivet.coffeeprince;

import java.util.Scanner;

public class CafeClass {
    public CategoryClass[] categories = new CategoryClass[2];
    public int[] orderQty = new int[25];
    public Scanner input = new Scanner(System.in);

    public CafeClass() {
        categories = CategoryClass.categories;
    }
    public int getValidInt() {
        while (true) {

            if (!input.hasNextInt()) {
                System.out.print("  ! Invalid input! Numbers only: ");
                input.next(); // removes wrong input
                continue;
            }

            return input.nextInt();
        }
    }
    public double getValidDouble() {
        while (true) {

            if (!input.hasNextDouble()) {
                System.out.print("  ! Invalid input! Numbers only: ");
                input.next();
                continue;
            }

            return input.nextDouble();
        }
    }

    public void openStore() {
        boolean running = true;

        while (running) {
            System.out.println();
            System.out.println("  +~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~+");
            System.out.println("  |       ~ COFFEE PRINCE ~         |");
            System.out.println("  +~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~+");
            System.out.println("  |  [1] Add Order                  |");
            System.out.println("  |  [2] Remove Order               |");
            System.out.println("  |  [3] View Cart                  |");
            System.out.println("  |  [4] Checkout                   |");
            System.out.println("  |  [5] Exit                       |");
            System.out.println("  +---------------------------------+");
            System.out.print("  Select Action: ");
            int choice = getValidInt();

            switch (choice) {
                case 1:
                    addOrder();
                    break;
                case 2:
                    removeOrder();
                    break;
                case 3:
                    viewCart();
                    break;
                case 4:
                    checkout();
                    break;
                case 5:
                    running = handleExit();
                    break;
                default:
                    System.out.println("Invalid input!");
                    break;
            }
        }
    }

    public void addOrder() {

        // ===== SHOW CATEGORIES FIRST =====
        System.out.println();
        System.out.println("  +=================================+");
        System.out.println("  |         SELECT CATEGORY         |");
        System.out.println("  +=================================+");

        for (int i = 0; i < categories.length; i++) {
            System.out.println("  |  " + (i + 1) + ". " + categories[i].categoryName);
        }
        System.out.println("  +=================================+");
        System.out.print("  Choose Category (1-5): ");
        int catChoice = getValidInt();

        while (catChoice < 1 || catChoice > categories.length) {
            System.out.println("  ! Invalid. Choose Category (1-5): ");
            System.out.print("  Choose Category (1-5): ");
            catChoice = getValidInt();
        }

        // ===== SHOW ITEMS OF SELECTED CATEGORY =====
        CategoryClass selectedCategory = categories[catChoice - 1];

        System.out.println();
        System.out.println("  +=================================+");
        System.out.println("  |   " + selectedCategory.categoryName);
        System.out.println("  +=================================+");

        for (int i = 0; i < selectedCategory.items.length; i++) {
            System.out.println("  |  " + (i + 1) + ". " + selectedCategory.items[i].name + " - P" + selectedCategory.items[i].price);
        }
        System.out.println("  +=================================+");
        System.out.print("  Select Item (1-5): ");
        int itemChoice = getValidInt();

        while (itemChoice < 1 || itemChoice > selectedCategory.items.length) {
            System.out.println("  ! Invalid. Try again.");
            System.out.print("  Select Item (1-5): ");
            itemChoice = getValidInt();
        }
        System.out.print("  Enter Quantity: ");
        int qty = getValidInt();

        while (qty < 1) {
            System.out.println("  ! Invalid. Try again.");
            System.out.print("  Enter Quantity: ");
            qty = getValidInt();
        }

        // ===== CONFIRMATION =====
        String selectedName = selectedCategory.items[itemChoice - 1].name;

        System.out.println();
        System.out.println("  +---------------------------------+");
        System.out.println("  |         CONFIRM ORDER           |");
        System.out.println("  +---------------------------------+");
        System.out.println("  |  Item : " + selectedName);
        System.out.println("  |  Qty  : " + qty);
        System.out.println("  +---------------------------------+");
        System.out.print("  Confirm order? (1=Yes / 2=No): ");
        int confirm = getValidInt();

        if (confirm == 1) {

            // Calculate global index for orderQty
            int globalIndex = 0;

            for (int i = 0; i < catChoice - 1; i++) {
                globalIndex += categories[i].items.length;
            }

            globalIndex += itemChoice - 1;

            orderQty[globalIndex] += qty;

            System.out.println();
            System.out.println("  >> Order added successfully!");
        } else {
            System.out.println();
            System.out.println("  >> Order cancelled.");
        }
    }

    public void removeOrder() {
        boolean hasOrders = false;
        for (int i = 0; i < orderQty.length; i++) {
            if (orderQty[i] > 0) {
                hasOrders = true;
            }
        }

        // If cart is empty, print message and return to menu
        if (hasOrders == false) {
            System.out.println();
            System.out.println("  ! No items in your cart.");
            return;
        }

        // Display the cart once
        System.out.println();
        System.out.println("  +=================================+");
        System.out.println("  |           YOUR CART             |");
        System.out.println("  +=================================+");
        int itemIndex = 0;
        for (int i = 0; i < categories.length; i++) {
            for (int j = 0; j < categories[i].items.length; j++) {
                if (orderQty[itemIndex] > 0) {
                    System.out.println("  |  " + (itemIndex + 1) + ". " + categories[i].items[j].name + " (Current Qty: " + orderQty[itemIndex] + ")");
                }
                itemIndex++;
            }
        }
        System.out.println("  +=================================+");

        // Ask for item to remove
        System.out.print("  Select Item to Remove (Enter the number): ");
        int itemChoice = getValidInt();

        // Loop if the item number is invalid or not in cart
        while (itemChoice < 1 || itemChoice > orderQty.length|| orderQty[itemChoice - 1] == 0) {
            System.out.println("  ! Invalid. Try again.");
            System.out.print("  Select Item to Remove (Enter the number): ");
            itemChoice = getValidInt();
        }

        // Ask for quantity to remove
        System.out.print("  Enter Quantity to Remove: ");
        int removeQty = getValidInt();

        // Loop if the quantity is invalid
        while (removeQty < 1 || removeQty > orderQty[itemChoice - 1]) {
            System.out.println("  ! Invalid. Try again.");
            System.out.print("  Enter Quantity to Remove: ");
            removeQty = getValidInt();
        }

        itemIndex = 0;
        String selectedName = "";

        for (int i = 0; i < categories.length; i++) {
            for (int j = 0; j < categories[i].items.length; j++) {

                if (itemIndex == itemChoice - 1) {
                    selectedName = categories[i].items[j].name;
                }

                itemIndex++;
            }
        }

        // Confirmation before removing
        System.out.println();
        System.out.println("  +---------------------------------+");
        System.out.println("  |        CONFIRM REMOVAL          |");
        System.out.println("  +---------------------------------+");
        System.out.println("  |  Item   : " + selectedName);
        System.out.println("  |  Remove : " + removeQty);
        System.out.println("  +---------------------------------+");
        System.out.print("  Are you sure? (1=Yes / 2=No): ");
        int confirm = getValidInt();

        while (confirm != 1 && confirm != 2) {
            System.out.println("  ! Invalid. Try again.");
            System.out.print("  Are you sure? (1=Yes / 2=No): ");
            confirm = getValidInt();
        }

        if (confirm == 1) {
            orderQty[itemChoice - 1] -= removeQty;
            System.out.println();
            System.out.println("  >> Order removed successfully!");
        } else {
            System.out.println();
            System.out.println("  >> Removal cancelled.");
        }
    }

    public void viewCart() {
        System.out.println();
        System.out.println("  +=================================+");
        System.out.println("  |           YOUR CART             |");
        System.out.println("  +=================================+");
        double total = 0;
        boolean hasOrders = false;
        int itemIndex = 0;

        for (int i = 0; i < categories.length; i++) {
            for (int j = 0; j < categories[i].items.length; j++) {
                if (orderQty[itemIndex] > 0) {
                    double subTotal = orderQty[itemIndex] * categories[i].items[j].price;
                    System.out.println("  |  " + categories[i].items[j].name + " - Qty: " + orderQty[itemIndex] + " - P" + subTotal);
                    total = total + subTotal;
                    hasOrders = true;
                }
                itemIndex++;
            }
        }

        if (hasOrders == false) {
            System.out.println("  |  Your cart is empty.");
            System.out.println("  +=================================+");
        } else {
            System.out.println("  +---------------------------------+");
            System.out.println("  |  Total Amount: P" + total);
            System.out.println("  +=================================+");
        }
    }

    public void checkout() {
        double total = 0;
        int itemIndex = 0;

        for (int i = 0; i < categories.length; i++) {
            for (int j = 0; j < categories[i].items.length; j++) {
                total = total + (orderQty[itemIndex] * categories[i].items[j].price);
                itemIndex++;
            }
        }

        // If no orders, print message and return to menu
        if (total == 0) {
            System.out.println();
            System.out.println("  ! No items in your cart.");
            return;
        }

        System.out.println();
        System.out.println("  +=================================+");
        System.out.println("  |            CHECKOUT             |");
        System.out.println("  +=================================+");

        // Show cart items before asking for cash
        itemIndex = 0;
        for (int i = 0; i < categories.length; i++) {
            for (int j = 0; j < categories[i].items.length; j++) {
                if (orderQty[itemIndex] > 0) {
                    double subTotal = orderQty[itemIndex] * categories[i].items[j].price;
                    System.out.println("  |  " + categories[i].items[j].name + " - Qty: " + orderQty[itemIndex] + " - P" + subTotal);
                }
                itemIndex++;
            }
        }

        System.out.println("  +---------------------------------+");
        System.out.println("  |  Total Bill: P" + total);
        System.out.println("  +=================================+");

        System.out.print("  Enter Cash Amount: P");
        double cash = getValidDouble();

        // Loop if cash is not enough
        while (cash < total) {
            System.out.println("  ! Not enough cash. Try again.");
            System.out.print("  Enter Cash Amount: P");
            cash = getValidDouble();
        }

        double change = cash - total;
        System.out.println();
        System.out.println("  +---------------------------------+");
        System.out.println("  |  Cash   : P" + cash);
        System.out.println("  |  Change : P" + change);
        System.out.println("  +---------------------------------+");
        System.out.println("  |  Payment Successful! Thank you! |");
        System.out.println("  +---------------------------------+");

        // Reset all orders after successful checkout
        for (int i = 0; i < orderQty.length; i++) {
            orderQty[i] = 0;
        }
    }

    public boolean handleExit() {
        boolean hasOrders = false;
        for (int i = 0; i < orderQty.length; i++) {
            if (orderQty[i] > 0) {
                hasOrders = true;
            }
        }

        System.out.println();
        if (hasOrders) {
            System.out.println("  ! You still have products in your cart.");
        }
        System.out.print("  Are you sure you want to exit? (1=Yes / 2=No): ");
        int confirm = getValidInt();

        while (confirm != 1 && confirm != 2) {
            System.out.println("  ! Invalid. Try again.");
            System.out.print("  Are you sure you want to exit? (1=Yes / 2=No): ");
            confirm = input.nextInt();
        }

        if (confirm == 1) {
            System.out.println();
            System.out.println("  +---------------------------------+");
            System.out.println("  |  Thanks for visiting Coffee     |");
            System.out.println("  |  Prince! See you next time! :)  |");
            System.out.println("  +---------------------------------+");
            return false;
        } else {
            return true;
        }
    }
}