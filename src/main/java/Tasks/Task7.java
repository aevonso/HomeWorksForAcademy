package Tasks;

import java.util.Scanner;

public class Task7 {
    public static void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n7 таска");
        System.out.print("Введи первое число: ");
        int a = scanner.nextInt();
        System.out.print("Введи второе число: ");
        int b = scanner.nextInt();

        int start = Math.min(a, b);
        int end = Math.max(a, b);

        System.out.printf("Нечётные числа в диапазоне [%d, %d]:\n", start, end);
        for (int i = start; i <= end; i++) {
            if (i % 2 != 0) {
                System.out.print(i + " ");
            }
        }
        System.out.println();
    }
}
