package streamAPI.task3;

import streamAPI.task3.model.Device;
import streamAPI.task3.service.DeviceAnalyzer;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Device> devices = List.of(
                new Device("iPhone 13", 2021, 999.99, "Black", "Смартфон"),
                new Device("Galaxy S21", 2021, 899.99, "White", "Смартфон"),
                new Device("MacBook Pro", 2022, 1999.99, "Silver", "Ноутбук"),
                new Device("iPad Air", 2020, 599.99, "Blue", "Планшет"),
                new Device("ThinkPad X1", 2022, 1499.99, "Black", "Ноутбук"),
                new Device("Surface Pro", 2021, 1299.99, "Black", "Планшет")
        );

        DeviceAnalyzer analyzer = new DeviceAnalyzer(devices);

        System.out.println("Все устройства");
        analyzer.getAllDevices().forEach(System.out::println);

        System.out.println("\n Устройства черного цвета");
        analyzer.getDevicesByColor("Black").forEach(System.out::println);

        System.out.println("\n Устройства 2021 года");
        analyzer.getDevicesByYear(2021).forEach(System.out::println);

        System.out.println("\n Устройства дороже 1000$");
        analyzer.getDevicesMoreExpensiveThan(1000).forEach(System.out::println);

        System.out.println("\n Все ноутбуки");
        analyzer.getDevicesByType("Ноутбук").forEach(System.out::println);

        System.out.println("\n Устройства 2020-2021 годов");
        analyzer.getDevicesInYearRange(2020, 2021).forEach(System.out::println);
    }
}
