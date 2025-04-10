package Tasks;

import java.util.Scanner;

public class Task8 {
    public static void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n8 таска");
        System.out.print("Введи начальное число диапазона: ");
        int start = scanner.nextInt();
        System.out.print("Введи конечное число диапазона: ");
        int end = scanner.nextInt();

        if (start > end) {
            int temp = start;
            start = end;
            end = temp;
        }

        for (int i = start; i <= end; i++) {
            System.out.println("Таблица умножения для числа " + i + ":");
            for (int j = 1; j <= 10; j++) {
                int result = i * j;
                System.out.print(i + " * " + j + " = " + result + "\t");
            }

        }
    }
}
