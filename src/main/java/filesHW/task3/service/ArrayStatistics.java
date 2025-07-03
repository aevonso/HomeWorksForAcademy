package filesHW.task3.service;

import filesHW.task3.model.ArrayData;

import java.util.Arrays;
import java.util.List;

public class ArrayStatistics {
    public void printAllStatistics(List<ArrayData> arrays) {
        for(int i = 0; i<arrays.size(); i++) {
            ArrayData arrayData = arrays.get(i);
            System.out.printf("Массив %d: %s\n", i+1, arrayData);
            System.out.printf("  Максимум: %d, Минимум: %d, Сумма: %d\n",
                    arrayData.getMax(), arrayData.getMin(), arrayData.getSum());
        }

        int max = arrays.stream()
                .mapToInt(ArrayData::getMax)
                .max().orElse(0);

        int min = arrays.stream()
                .mapToInt(ArrayData::getMin)
                .min().orElse(0);

        int totalSum = arrays.stream()
                .mapToInt(ArrayData::getSum)
                .sum();

        System.out.println("\n=== Общая статистика ===");
        System.out.printf("Максимум среди всех массивов: %d\n", max);
        System.out.printf("Минимум среди всех массивов: %d\n", min);
        System.out.printf("Общая сумма всех массивов: %d\n", totalSum);
    }
}
