package hw2;

//методы в main некоторые дописал с gpt, т.к это просто смерть самому писать, а именно свичи ))

import hw2.database.TaxDatabase;
import hw2.models.Fine;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        TaxDatabase db = new TaxDatabase();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            printMenu();
            int choice = getIntInput(scanner, "Выберите действие: ");
            scanner.nextLine();

            switch (choice) {
                case 1:
                    db.printAll();
                    break;
                case 2:
                    String id = getStringInput(scanner, "Введите идентификационный код: ");
                    db.printById(id);
                    break;
                case 3:
                    String type = getStringInput(scanner, "Введите тип штрафа: ");
                    db.printByFineType(type);
                    break;
                case 4:
                    String city = getStringInput(scanner, "Введите город: ");
                    db.PrintByCity(city);
                    break;
                case 5:
                    addNewPerson(scanner, db);
                    break;
                case 6:
                    addFineToPerson(scanner, db);
                    break;
                case 7:
                    removeFine(scanner, db);
                    break;
                case 8:
                    updatePerson(scanner, db);
                    break;
                case 0:
                    System.out.println("Выход из программы");
                    scanner.close();
                    return;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\nМеню:");
        System.out.println("1. Полная распечатка базы данных");
        System.out.println("2. Распечатка данных по конкретному коду");
        System.out.println("3. Распечатка данных по конкретному типу штрафа");
        System.out.println("4. Распечатка данных по конкретному городу");
        System.out.println("5. Добавление нового человека");
        System.out.println("6. Добавление штрафа существующему человеку");
        System.out.println("7. Удаление штрафа");
        System.out.println("8. Замена информации о человеке");
        System.out.println("0. Выход");
    }

    private static void addNewPerson(Scanner scanner, TaxDatabase db) {
        String newId = getStringInput(scanner, "Введите идентификационный код: ");
        String name = getStringInput(scanner, "Введите имя: ");
        String address = getStringInput(scanner, "Введите адрес: ");
        db.addPerson(newId, name, address);
    }

    private static void addFineToPerson(Scanner scanner, TaxDatabase db) {
        String existingId = getStringInput(scanner, "Введите идентификационный код: ");
        String fineType = getStringInput(scanner, "Введите тип штрафа: ");
        double amount = getDoubleInput(scanner, "Введите сумму штрафа: ");
        String fineCity = getStringInput(scanner, "Введите город: ");
        db.addFineToPerson(existingId, fineType, amount, fineCity, new Date());
    }

    private static void removeFine(Scanner scanner, TaxDatabase db) {
        String idToRemove = getStringInput(scanner, "Введите идентификационный код: ");
        int fineIndex = getIntInput(scanner, "Введите индекс штрафа для удаления: ");
        db.removeFine(idToRemove, fineIndex);
    }

    private static void updatePerson(Scanner scanner, TaxDatabase db) {
        String idToUpdate = getStringInput(scanner, "Введите идентификационный код: ");
        String newName = getStringInput(scanner, "Введите новое имя: ");
        String newAddress = getStringInput(scanner, "Введите новый адрес: ");

        System.out.println("Введите новые штрафы (введите 'готово' для завершения):");
        List<Fine> newFines = new ArrayList<>();
        while (true) {
            String ft = getStringInput(scanner, "Тип штрафа (или 'готово'): ");
            if (ft.equalsIgnoreCase("готово")) break;

            double amt = getDoubleInput(scanner, "Сумма: ");
            scanner.nextLine();
            String ct = getStringInput(scanner, "Город: ");
            newFines.add(new Fine(ft, amt, ct, new Date()));
        }

        db.updatePerson(idToUpdate, newName, newAddress, newFines);
    }

    private static String getStringInput(Scanner scanner, String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    private static int getIntInput(Scanner scanner, String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.println("Пожалуйста, введите целое число");
            System.out.print(prompt);
            scanner.next();
        }
        return scanner.nextInt();
    }

    private static double getDoubleInput(Scanner scanner, String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextDouble()) {
            System.out.println("Пожалуйста, введите число");
            System.out.print(prompt);
            scanner.next();
        }
        return scanner.nextDouble();
    }
}
