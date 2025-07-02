package streamAPI.task1.generator;

import java.util.Random;

public class NumberGenerator {
    public static int[] generateRandomNumbers(int count, int min, int max) {
        return new Random().ints(count, min, max).toArray();
    }
}
