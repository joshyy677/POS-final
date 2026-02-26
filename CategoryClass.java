package olivet.coffeeprince;

public class CategoryClass {
    public String categoryName; 
    public ProductClass[] items;

    public CategoryClass(String name, ProductClass[] productList) {
        categoryName = name;
        items = productList;
    }
    public static CategoryClass[] categories = {
            new CategoryClass("HOT COFFEE", ProductClass.hotCoffee),
            new CategoryClass("COLD COFFEE", ProductClass.coldCoffee),
            new CategoryClass("TEA", ProductClass.tea),
            new CategoryClass("PASTRIES", ProductClass.pastries),
            new CategoryClass("CAKES", ProductClass.cakes)
    };
}