package filesHW.task3.service;

import filesHW.task3.model.ArrayData;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayFileReader {
    public List<ArrayData> readArraysFromFile(String filePath) throws IOException {
        List<ArrayData> arrays = new ArrayList<>();

        Files.lines(Path.of(filePath))
                .filter(line -> !line.trim().isEmpty())
                .forEach(line -> {
                    int[] numbers = Arrays.stream(line.split("\\s+"))
                            .mapToInt(Integer::parseInt)
                            .toArray();
                    arrays.add(new ArrayData(numbers));
                });

        return arrays;
    }
}
