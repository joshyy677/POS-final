package olivet.coffeeprince;

public class ProductClass {
    public String name;
    public double price;

    public ProductClass(String n, double p) {
        name = n;
        price = p;
    }
    public static ProductClass[] hotCoffee = {
            new ProductClass("Latte", 120.0),
            new ProductClass("Americano", 90.0),
            new ProductClass("Cappuccino", 130.0),
            new ProductClass("Mocha", 140.0),
            new ProductClass("Flat White", 125.0)
    };

    public static ProductClass[] coldCoffee = {
            new ProductClass("Iced Latte", 130.0),
            new ProductClass("Iced Americano", 100.0),
            new ProductClass("Iced Mocha", 150.0),
            new ProductClass("Cold Brew", 160.0),
            new ProductClass("Vanilla Iced Coffee", 145.0)
    };

    public static ProductClass[] tea = {
            new ProductClass("Green Tea", 80.0),
            new ProductClass("Milk Tea", 110.0),
            new ProductClass("Black Tea", 75.0),
            new ProductClass("Matcha Latte", 150.0),
            new ProductClass("Chai Latte", 140.0)
    };

    public static ProductClass[] pastries = {
            new ProductClass("Donut", 50.0),
            new ProductClass("Croissant", 60.0),
            new ProductClass("Muffin", 70.0),
            new ProductClass("Cinnamon Roll", 85.0),
            new ProductClass("Banana Bread", 90.0)
    };

    public static ProductClass[] cakes = {
            new ProductClass("Cheesecake", 150.0),
            new ProductClass("Chocolate Cake", 140.0),
            new ProductClass("Carrot Cake", 135.0),
            new ProductClass("Red Velvet", 155.0),
            new ProductClass("Tiramisu", 160.0)
    };
}