package hw2.models;

import java.util.Date;

public class Fine {
    private String type;
    private double amount;
    private String city;
    private Date date;

    public Fine(String type, double amount, String city, Date date) {
        this.type = type;
        this.amount = amount;
        this.city = city;
        this.date = date;
    }

    public String getType() {
        return  type;
    }
    public double getAmount() {
        return amount;
    }
    public String getCity() {
        return  city;
    }
    public Date getDate() {
        return  date;
    }

    @Override
    public String toString() {
        return "Тип: " + type + ", Сумма: " + amount + ", Город: " +city + ", Дата: " + date;
    }
}
