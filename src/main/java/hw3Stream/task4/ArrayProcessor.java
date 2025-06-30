package hw3Stream.task4;
import java.util.function.Predicate;

public class ArrayProcessor {
    public static int sumIf(int[] array, Predicate<Integer> condition) {
        int sum = 0;
        for (int num : array) {
            if (condition.test(num)) {
                sum += num;
            }
        }
        return sum;
    }
}
