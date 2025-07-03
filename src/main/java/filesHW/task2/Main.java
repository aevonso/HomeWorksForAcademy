package filesHW.task2;

import filesHW.task2.model.FileLineInfo;
import filesHW.task2.service.FileLongestLineFinder;

import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите путь к файлу: ");
        String filePath = scanner.nextLine();

        try {
            FileLongestLineFinder finder = new FileLongestLineFinder();
            Optional<FileLineInfo> result = finder.findLongestLine(filePath);

            if (result.isPresent()) {
                System.out.println("\nСамая длинная строка в файле:");
                System.out.println(result.get());
            } else {
                System.out.println("Файл пуст или не содержит строк");
            }
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
