package hw3Stream.task2;

import hw3Stream.task2.models.Fraction;

import java.util.function.BiFunction;

public class FractionOperations {
    //+
    public static final BiFunction<Fraction, Fraction, Fraction> ADD = (f1, f2) -> {
        int newNum = f1.getNumerator() * f2.getDenominator() + f2.getNumerator() * f1.getDenominator();
        int newDen = f1.getDenominator() * f2.getDenominator();
        return new Fraction(newNum, newDen);
    };

    // -
    public static final BiFunction<Fraction, Fraction, Fraction> SUBTRACT = (f1, f2) -> {
        int newNum = f1.getNumerator() * f2.getDenominator() - f2.getNumerator() * f1.getDenominator();
        int newDen = f1.getDenominator() * f2.getDenominator();
        return new Fraction(newNum, newDen);
    };

    //*
    public static final BiFunction<Fraction, Fraction, Fraction> MULTIPLY = (f1, f2) ->
            new Fraction(f1.getNumerator() * f2.getNumerator(),
                    f1.getDenominator() * f2.getDenominator());

    //:
    public static final BiFunction<Fraction, Fraction, Fraction> DIVIDE = (f1, f2) -> {
        if (f2.getNumerator() == 0) {
            throw new ArithmeticException("Деление на ноль невозможно");
        }
        return new Fraction(f1.getNumerator() * f2.getDenominator(),
                f1.getDenominator() * f2.getNumerator());
    };
}
