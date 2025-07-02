package streamAPI.task1;

import streamAPI.task1.analyzer.NumberAnalyzer;
import streamAPI.task1.generator.NumberGenerator;

public class Main {
    public static void main(String[] args) {
        int[] numbers = NumberGenerator.generateRandomNumbers(20, -1000, 1000);

        NumberAnalyzer analyzer = new NumberAnalyzer(numbers);

        System.out.println("Сгенерированные числа");
        analyzer.getNumbersStream().forEach(n-> System.out.print(n+ " "));

        System.out.println("\n\nАнализ чисел");
        System.out.println("Кол-во положительных " + analyzer.countPositivchik());
        System.out.println("Кол-во отрицательных " + analyzer.negativchik());
        System.out.println("Кол-во двузначных " + analyzer.countTwoDigit());
        System.out.println("Кол-во зеркальных " + analyzer.countPalindromes());


    }
}
