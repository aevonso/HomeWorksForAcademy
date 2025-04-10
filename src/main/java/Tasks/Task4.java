package Tasks;

import java.util.Scanner;

public class Task4 {

    public static void run() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n4 таска");
        System.out.print("Введи 6 значное число живо: ");
        String input = sc.nextLine();

        if (input.length() != 6 || !input.matches("\\d+")) {
            System.out.println("Ты че дебил?");
            return;
        }
        //если все ок

        char[] cifr = input.toCharArray();

        //переброска
        char temp = cifr[0];
        cifr[0] = cifr[5];
        cifr[5] = temp;

        temp = cifr[1];
        cifr[1] = cifr[4];
        cifr[4] = temp;

        String result = new String(cifr);
        System.out.println("Результат: " + result);
    }
}
