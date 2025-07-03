package filesHW.task3;

import filesHW.task3.model.ArrayData;
import filesHW.task3.service.ArrayFileReader;
import filesHW.task3.service.ArrayStatistics;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите путь к файлу: ");
        String filePath = scanner.nextLine();

        try {
            ArrayFileReader reader = new ArrayFileReader();
            List<ArrayData> arrays = reader.readArraysFromFile(filePath);

            ArrayStatistics statistics = new ArrayStatistics();
            statistics.printAllStatistics(arrays);
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
