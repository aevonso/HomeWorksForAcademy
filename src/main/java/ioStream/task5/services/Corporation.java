package ioStream.task5.services;

import ioStream.task5.models.Employee;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;

public class Corporation {
    private List<Employee> employees = new ArrayList<>();
    private List<Employee> lastSearchResult = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);
    private Path defaultSavePath = Paths.get("corporation_data.ser");

    public void start() {
        loadEmployees();

        while (true) {
            printMenu();
            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1 -> addEmployee();
                    case 2 -> editEmployee();
                    case 3 -> deleteEmployee();
                    case 4 -> searchByLastName();
                    case 5 -> searchByAgeOrLetter();
                    case 6 -> saveFoundEmployees();
                    case 7 -> saveAllEmployees();
                    case 8 -> {
                        autoSave();
                        return;
                    }
                    default -> System.out.println("Неверный выбор!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Введите число от 1 до 8!");
            } catch (Exception e) {
                System.out.println("Ошибка: " + e.getMessage());
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
        System.out.println("6. Сохранить найденных сотрудников");
        System.out.println("7. Сохранить всех сотрудников");
        System.out.println("8. Выход");
        System.out.print("Выберите действие: ");
    }

    private void loadEmployees() {
        try {
            if (Files.exists(defaultSavePath)) {
                try (ObjectInputStream ois = new ObjectInputStream(
                        Files.newInputStream(defaultSavePath))) {
                    employees = (List<Employee>) ois.readObject();
                    System.out.printf("Загружено %d сотрудников\n", employees.size());
                }
            }
        } catch (Exception e) {
            System.out.println("Ошибка загрузки: " + e.getMessage());
        }
    }

    private void autoSave() {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                Files.newOutputStream(defaultSavePath))) {
            oos.writeObject(employees);
            System.out.println("Данные автоматически сохранены");
        } catch (Exception e) {
            System.out.println("Ошибка автосохранения: " + e.getMessage());
        }
    }

    private void saveAllEmployees() {
        System.out.print("Введите путь для сохранения (Enter для default): ");
        String filename = scanner.nextLine().trim();
        Path path = filename.isEmpty() ? defaultSavePath : Paths.get(filename);

        try {
            Files.createDirectories(path.getParent());
            try (ObjectOutputStream oos = new ObjectOutputStream(
                    Files.newOutputStream(path))) {
                oos.writeObject(employees);
                System.out.printf("Сохранено %d сотрудников в %s\n",
                        employees.size(), path.toAbsolutePath());
            }
        } catch (Exception e) {
            System.out.println("Ошибка сохранения: " + e.getMessage());
        }
    }

    private void addEmployee() {
        System.out.println("\nДобавление сотрудника:");
        System.out.print("Фамилия: ");
        String lastname = scanner.nextLine();
        System.out.print("Имя: ");
        String firstname = scanner.nextLine();
        System.out.print("Возраст: ");
        int age = Integer.parseInt(scanner.nextLine());
        System.out.print("Отдел: ");
        String department = scanner.nextLine();

        employees.add(new Employee(lastname, firstname, age, department));
        System.out.println("Сотрудник добавлен!");
    }

    private void editEmployee() {
        System.out.print("\nФамилия для поиска: ");
        String lastname = scanner.nextLine();

        Employee emp = findEmployee(lastname);
        if (emp == null) {
            System.out.println("Сотрудник не найден!");
            return;
        }

        System.out.println("Найден: " + emp);
        System.out.println("Введите новые данные:");

        System.out.print("Фамилия [" + emp.getLastname() + "]: ");
        String newLastname = scanner.nextLine();
        if (!newLastname.isEmpty()) emp.setLastname(newLastname);

        System.out.print("Имя [" + emp.getFirstname() + "]: ");
        String newFirstname = scanner.nextLine();
        if (!newFirstname.isEmpty()) emp.setFirstname(newFirstname);

        System.out.print("Возраст [" + emp.getAge() + "]: ");
        String ageInput = scanner.nextLine();
        if (!ageInput.isEmpty()) emp.setAge(Integer.parseInt(ageInput));

        System.out.print("Отдел [" + emp.getDepartment() + "]: ");
        String newDept = scanner.nextLine();
        if (!newDept.isEmpty()) emp.setDepartment(newDept);

        System.out.println("Данные обновлены!");
    }

    private void deleteEmployee() {
        System.out.print("\nФамилия для удаления: ");
        String lastname = scanner.nextLine();

        if (employees.removeIf(e -> e.getLastname().equalsIgnoreCase(lastname))) {
            System.out.println("Сотрудник удален");
        } else {
            System.out.println("Сотрудник не найден!");
        }
    }

    private Employee findEmployee(String lastname) {
        return employees.stream()
                .filter(e -> e.getLastname().equalsIgnoreCase(lastname))
                .findFirst()
                .orElse(null);
    }

    private void searchByLastName() {
        System.out.print("\nФамилия для поиска: ");
        String lastname = scanner.nextLine();

        lastSearchResult = employees.stream()
                .filter(e -> e.getLastname().equalsIgnoreCase(lastname))
                .collect(Collectors.toList());

        printSearchResults();
    }

    private void searchByAgeOrLetter() {
        System.out.println("\nПоиск по: 1 - возрасту, 2 - букве фамилии");
        int choice = Integer.parseInt(scanner.nextLine());

        if (choice == 1) {
            System.out.print("Введите возраст: ");
            int age = Integer.parseInt(scanner.nextLine());
            lastSearchResult = employees.stream()
                    .filter(e -> e.getAge() == age)
                    .collect(Collectors.toList());
        } else {
            System.out.print("Введите букву: ");
            char letter = scanner.nextLine().charAt(0);
            lastSearchResult = employees.stream()
                    .filter(e -> Character.toLowerCase(e.getLastname().charAt(0))
                            == Character.toLowerCase(letter))
                    .collect(Collectors.toList());
        }

        printSearchResults();
    }

    private void printSearchResults() {
        if (lastSearchResult.isEmpty()) {
            System.out.println("Сотрудники не найдены!");
        } else {
            System.out.printf("Найдено %d сотрудников:\n", lastSearchResult.size());
            lastSearchResult.forEach(System.out::println);
        }
    }

    private void saveFoundEmployees() {
        if (lastSearchResult.isEmpty()) {
            System.out.println("Нет результатов для сохранения!");
            return;
        }

        System.out.print("Введите путь для сохранения (Enter для default): ");
        String filename = scanner.nextLine().trim();
        Path path = filename.isEmpty() ?
                Paths.get("search_results.ser") : Paths.get(filename);

        try {
            Files.createDirectories(path.getParent());
            try (ObjectOutputStream oos = new ObjectOutputStream(
                    Files.newOutputStream(path))) {
                oos.writeObject(lastSearchResult);
                System.out.printf("Сохранено %d сотрудников в %s\n",
                        lastSearchResult.size(), path.toAbsolutePath());
            }
        } catch (Exception e) {
            System.out.println("Ошибка сохранения: " + e.getMessage());
        }
    }
}