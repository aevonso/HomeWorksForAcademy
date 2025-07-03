package filesHW.task2.model;

public record FileLineInfo(String line, int length) {
    @Override
    public String toString() {
        return "Длина: " + length + " | Строка: \"" + line + "\"";
    }

}
