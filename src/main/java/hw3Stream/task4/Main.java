package hw3Stream.task4;

import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        int[] data = { -10, -7, -3, 0, 1, 3, 5, 7, 10, 13 };

        Predicate<Integer> equalsFive = n -> n == 5;
        Predicate<Integer> outsideRange = n -> n < -5 || n > 5;
        Predicate<Integer> isPositive = n -> n > 0;
        Predicate<Integer> isNegative = n -> n < 0;

        System.out.println("Сумма равных 5: " +
                ArrayProcessor.sumIf(data, equalsFive));

        System.out.println("Сумма вне диапазона -5,5: " +
                ArrayProcessor.sumIf(data, outsideRange));

        System.out.println("Сумма положительных: " +
                ArrayProcessor.sumIf(data, isPositive));

        System.out.println("Сумма отрицательных: " +
                ArrayProcessor.sumIf(data, isNegative));
    }
}