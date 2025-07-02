package streamAPI.task2.model;

public class Product {
    private final String name;
    private final String category;

    public Product(String name, String category) {
        this.name = name;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public  String toString() {
        return name + " (" + category + ")";
    }
}
