package hw3Stream.task2;

import hw3Stream.task2.models.Fraction;

public class Main {
    public static void main(String[] args) {
        Fraction f1 = new Fraction(1, 2);
        Fraction f2 = new Fraction(3, 4);

        System.out.println("Дробь 1: " + f1);
        System.out.println("Дробь 2: " + f2);
        System.out.println();

        System.out.println("Сумма: " + FractionOperations.ADD.apply(f1, f2));
        System.out.println("Разность: " + FractionOperations.SUBTRACT.apply(f1, f2));
        System.out.println("Произведение: " + FractionOperations.MULTIPLY.apply(f1, f2));
        System.out.println("Частное: " + FractionOperations.DIVIDE.apply(f1, f2));
    }
}
