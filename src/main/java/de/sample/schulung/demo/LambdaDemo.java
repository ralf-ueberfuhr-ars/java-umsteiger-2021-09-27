package de.sample.schulung.demo;

import java.util.function.Supplier;

public class LambdaDemo {

    // 1) Summiere 2 Zahlen
    private static double sum(double x, double y) {
        return x + y;
    }

    // 2) Summiere Zahlen, bis die Summe >=1 ist
    @FunctionalInterface
    private static interface NumberGenerator {
        double generate();
    }

    private static double sum2(NumberGenerator generator) {
        double sum = 0;
        while (sum < 1) {
            sum += generator.generate();
        }
        return sum;
    }

    // Was gibt es im Standard?
    private static double sum3(Supplier<Double> generator) {
        double sum = 0;
        while (sum < 1) {
            sum += generator.get();
        }
        return sum;
    }

    public static void main(String[] args) {
        // Die Herkunft der Zahlen wird HIER festgelegt
        System.out.println(sum(2.6, 1.8));
        System.out.println(sum(Math.random(), Math.random()));
        System.out.println(sum2(new NumberGenerator() {
            @Override
            public double generate() {
                return Math.random();
            }
        }));
        // Lambdas
        System.out.println(sum2(() -> {
            return Math.random();
        }));
        // Spezialfall: nur 1 Anweisung
        System.out.println(sum2(() -> Math.random()));
        // Spezialfall: Parameter-Match
        System.out.println(sum2(Math::random)); // Method Reference Operator
        System.out.println(sum3(Math::random)); // Method Reference Operator

    }

}
