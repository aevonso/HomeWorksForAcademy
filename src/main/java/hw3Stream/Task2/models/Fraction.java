package hw3Stream.task2.models;

public class Fraction {
    private final int numerator;
    private final int denominator;

    public Fraction(int numerator, int denominator) {
        if(denominator==0) {
            throw new IllegalArgumentException("Знаменатель не может быть 0");
        }
        this.numerator = numerator;
        this.denominator = denominator;
    }
    public int getNumerator() {
        return  numerator;
    }
    public int getDenominator() {
        return denominator;
    }
    @Override
    public String toString() {
        return  numerator + "/" + denominator;
    }
}
