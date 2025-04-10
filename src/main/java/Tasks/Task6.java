package Tasks;

import java.util.Scanner;

public class Task6 {
    public static void run() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n6 таска");
        System.out.print("Введи кол-во метров: ");
        double meters = scanner.nextDouble();

        System.out.println("Во что переводить?");
        System.out.println("1) в мили");
        System.out.println("2) в дюймы");
        System.out.println("3) в ярды");
        System.out.print("Сделай выбор (1/2/3): ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                double miles = meters * 0.000621371;
                System.out.println(meters + " м = " + miles + " миль");
                break;
            case 2:
                double inches = meters * 39.3701;
                System.out.println(meters + " м = " + inches + " дюймов");
                break;
            case 3:
                double yards = meters * 1.09361;
                System.out.println(meters + " м = " + yards + " ярдов");
                break;
            default:
                System.out.println("Неверный выбор! Попробуй снова, loshara");
        }
    }
}
