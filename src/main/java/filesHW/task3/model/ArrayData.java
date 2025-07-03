package filesHW.task3.model;

import java.util.Arrays;

public class ArrayData {
    private final int[] array;

    public ArrayData(int[] array) {
        this.array = array;
    }

    public int[] getArray() {
        return array;
    }

    public int getMax() {
        return Arrays.stream(array).max().orElse(0);
    }

    public int getMin() {
        return Arrays.stream(array).min().orElse(0);
    }

    public int getSum() {
        return Arrays.stream(array).sum();
    }

    @Override
    public String toString() {
        return Arrays.toString(array);
    }
}
