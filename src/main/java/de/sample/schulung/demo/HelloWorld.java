package de.sample.schulung.demo;

import de.sample.schulung.daten.Person;

public class HelloWorld {

    // kein const, kein readonly -> nur final
    public static final int COUNT_OF_DAYS = 365;

    public static void main(String[] args) {
        System.out.println("de.sample.schulung.demo.HelloWorld");
        System.out.println("de.sample.schulung.demo.HelloWorld");
        System.out.println("de.sample.schulung.demo.HelloWorld");
        System.out.println("de.sample.schulung.demo.HelloWorld");
        System.out.println("de.sample.schulung.demo.HelloWorld");
    }

    // Methoden und Variablen in camelCase
    void doSomething() {
        // Type Aliasing: int = System.Int32 -> in Java NICHT
        // int hat auch keine Methoden -> primitiven Datentypen
        final int countOfThings = 3;
        // int x = null; // geht nicht
        // weitere primitive Datentypen: long, int, short, byte / double, float / char / boolean
        char c = 'a';
        boolean b = true;
        // primitiv -> Vergleich mit ==
        boolean isCountEqual = countOfThings == 5;
        // Klasse Person aus anderem Package
        Person p = new Person();
        p.toString();
        boolean isPersonEqual = p.equals(new Person());
        boolean isPersonSame = p == new Person();
    }

}
