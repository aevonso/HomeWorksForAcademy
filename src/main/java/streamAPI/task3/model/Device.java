package streamAPI.task3.model;


public class Device {
    private final String name;
    private final int year;
    private final double price;
    private final String color;
    private final String type;

    public Device(String name, int year, double price, String color, String type) {
        this.name = name;
        this.year = year;
        this.price = price;
        this.color = color;
        this.type = type;
    }

    public String getName() { return name; }
    public int getYear() { return year; }
    public double getPrice() { return price; }
    public String getColor() { return color; }
    public String getType() { return type; }

    @Override
    public String toString() {
        return String.format("%s (Тип: %s, Год: %d, Цена: %.2f, Цвет: %s)",
                name, type, year, price, color);
    }
}
