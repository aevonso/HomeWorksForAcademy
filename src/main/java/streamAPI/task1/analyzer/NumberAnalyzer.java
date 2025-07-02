package streamAPI.task1.analyzer;

import java.util.Arrays;
import java.util.function.Predicate;
import java.util.stream.IntStream;

public class NumberAnalyzer {
    private final int[] numbers;

    public NumberAnalyzer(int[] numbers) {
        this.numbers = numbers;
    }
    private long countByCondition(Predicate<Integer> condition) {
        return Arrays.stream(numbers).filter(condition::test).count();
    }

    public long countPositivchik() {
        return countByCondition(n->n>0);
    }

    public long negativchik() {
        return countByCondition(n->n<0);
    }

    public long countTwoDigit() {
        return countByCondition(n->(n>=10&&n<=99) || (n<=-10 && n>=-99));
    }

    private boolean isPalindrome(int number) {
        String numbers = Integer.toString(Math.abs(number));
        return numbers.equals((new StringBuilder(numbers).reverse().toString()));
    }

    public long countPalindromes() {
        return countByCondition(this::isPalindrome);
    }

    public IntStream getNumbersStream() {
        return Arrays.stream(numbers);
    }
}
