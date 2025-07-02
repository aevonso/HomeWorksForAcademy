package streamAPI.task2;

import streamAPI.task2.model.Product;
import streamAPI.task2.service.ProductAnalyzer;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Product> products = Arrays.asList(
                new Product("Молоко", "Молоко"),
                new Product("Сыр", "Молочные продукты"),
                new Product("Хлеб", "Хлебобулочные"),
                new Product("Масло", "Молоко"),
                new Product("Йогурт", "Молоко"),
                new Product("Кефир", "Молоко"),
                new Product("Сок", "Напитки"),
                new Product("Молоко", "Молоко"),
                new Product("Чай", "Напитки"),
                new Product("Кофе", "Напитки")
        );

        ProductAnalyzer analyzer = new ProductAnalyzer(products);
        Scanner scanner = new Scanner(System.in);

        System.out.println("Все продукты: ");
        analyzer.getAllProducts().forEach(System.out::println);

        System.out.println("\nПродукты с названием меньше 5 символов:");
        analyzer.getShortNamedProducts().forEach(System.out::println);

        System.out.print("\nВведите название продукта для подсчёта: ");
        String productName = scanner.nextLine();
        System.out.println("Количество вхождений: " +
                analyzer.countProductOccurrences(productName));

        System.out.print("\nВведите букву для поиска: ");
        char letter = scanner.nextLine().charAt(0);
        System.out.println("Продукты на букву '" + letter + "':");
        analyzer.getProductsStartigLetter(letter).forEach(System.out::println);

        System.out.println("\nВсе продукты категории 'Молоко':");
        analyzer.getMilkProducts().forEach(System.out::println);
    }
}
