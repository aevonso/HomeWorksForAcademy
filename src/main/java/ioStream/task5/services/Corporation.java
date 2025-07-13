package ioStream.task5.services;

import ioStream.task5.models.Employee;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Corporation {
    private List<Employee> employees = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);
    private List<Employee> lastSearchResult = new ArrayList<>();

    public void start() {
        loadEmployees();

        while (true) {
            printMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addEmployee();
                case 2 -> editEmployee();
                case 3 -> deleteEmployee();
                case 4 -> searchByLastName();
                case 5 -> searchByAgeOrLetter();
                case 6 -> saveFoundEmployees();
                case 7 -> saveAllEmployees();
                case 8 -> {
                    saveAllEmployees();
                    return;
                }
                default -> System.out.println("Неверный выбор!");
            }
        }
    }

    private void printMenu() {
        System.out.println("\n=== Корпорация ===");
        System.out.println("1. Добавить сотрудника");
        System.out.println("2. Редактировать сотрудника");
        System.out.println("3. Удалить сотрудника");
        System.out.println("4. Поиск по фамилии");
        System.out.println("5. Поиск по возрасту или букве фамилии");
        System.out.println("6. Сохранить найденных сотрудников в файл");
        System.out.println("7. Сохранить всех сотрудников в файл");
        System.out.println("8. Выход");
        System.out.print("Выберите действие: ");
    }

    private void loadEmployees() {
        System.out.print("Введите путь к файлу для загрузки: ");
        String filename = scanner.nextLine();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            employees = (List<Employee>) ois.readObject();
            System.out.println("Данные загружены. Загружено сотрудников: " + employees.size());
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден. Будет создан новый список сотрудников.");
        } catch (Exception e) {
            System.out.println("Ошибка при загрузке файла: " + e.getMessage());
        }
    }

    private void saveAllEmployees() {
        System.out.print("Введите путь для сохранения (без кавычек): ");
        String filename = scanner.nextLine().trim().replace("\"", ""); // Удаляем кавычки

        try {
            Path path = Paths.get(filename);
            Files.createDirectories(path.getParent());

            try (ObjectOutputStream oos = new ObjectOutputStream(
                    new FileOutputStream(path.toFile()))) {
                oos.writeObject(employees);
                System.out.println("Данные сохранены успешно в: " + path.toAbsolutePath());
            }
        } catch (Exception e) {
            System.err.println("Ошибка при сохранении: " + e.getMessage());
            e.printStackTrace();
        }
    }
    private void addEmployee() {
        System.out.println("\nДобавление нового сотрудника:");
        System.out.print("Фамилия: ");
        String lastname = scanner.nextLine();
        System.out.print("Имя: ");
        String firstname = scanner.nextLine();
        System.out.print("Возраст: ");
        int age = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Отдел: ");
        String department = scanner.nextLine();

        employees.add(new Employee(lastname, firstname, age, department));
        System.out.println("Сотрудник добавлен!");
    }

    private void editEmployee() {
        System.out.print("\nВведите фамилию сотрудника для редактирования: ");
        String lastname = scanner.nextLine();

        Employee found = findEmployee(lastname);
        if (found == null) {
            System.out.println("Сотрудник не найден!");
            return;
        }

        System.out.println("Найден: " + found);
        System.out.println("Введите новые данные:");

        System.out.print("Фамилия: ");
        found.setLastname(scanner.nextLine());
        System.out.print("Имя: ");
        found.setFirstname(scanner.nextLine());
        System.out.print("Возраст: ");
        found.setAge(scanner.nextInt());
        scanner.nextLine();
        System.out.print("Отдел: ");
        found.setDepartment(scanner.nextLine());

        System.out.println("Данные обновлены!");
    }

    private void deleteEmployee() {
        System.out.print("\nВведите фамилию сотрудника для удаления: ");
        String lastname = scanner.nextLine();

        Employee found = findEmployee(lastname);
        if (found == null) {
            System.out.println("Сотрудник не найден!");
            return;
        }

        employees.remove(found);
        System.out.println("Сотрудник удален!");
    }

    private Employee findEmployee(String lastname) {
        return employees.stream()
                .filter(e -> e.getLastname().equalsIgnoreCase(lastname))
                .findFirst()
                .orElse(null);
    }

    private void searchByLastName() {
        System.out.print("\nВведите фамилию для поиска: ");
        String lastname = scanner.nextLine();

        lastSearchResult = employees.stream()
                .filter(e -> e.getLastname().equalsIgnoreCase(lastname))
                .toList();

        if (lastSearchResult.isEmpty()) {
            System.out.println("Сотрудник не найден!");
        } else {
            lastSearchResult.forEach(System.out::println);
        }
    }

    private void searchByAgeOrLetter() {
        System.out.println("\nПоиск по: 1 - возрасту, 2 - букве фамилии");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 1) {
            System.out.print("Введите возраст: ");
            int age = scanner.nextInt();
            scanner.nextLine();

            lastSearchResult = employees.stream()
                    .filter(e -> e.getAge() == age)
                    .toList();
        } else if (choice == 2) {
            System.out.print("Введите букву: ");
            char letter = scanner.nextLine().charAt(0);

            lastSearchResult = employees.stream()
                    .filter(e -> e.getLastname().toLowerCase().startsWith(String.valueOf(letter).toLowerCase()))
                    .toList();
        }

        if (lastSearchResult.isEmpty()) {
            System.out.println("Сотрудники не найдены!");
        } else {
            lastSearchResult.forEach(System.out::println);
        }
    }

    private void saveFoundEmployees() {
        if (lastSearchResult.isEmpty()) {
            System.out.println("Нет результатов поиска для сохранения!");
            return;
        }

        System.out.print("Введите путь для сохранения: ");
        String filename = scanner.nextLine();

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(lastSearchResult);
            System.out.println("Результаты поиска сохранены успешно!");
        } catch (Exception e) {
            System.out.println("Ошибка при сохранении: " + e.getMessage());
        }
    }
}