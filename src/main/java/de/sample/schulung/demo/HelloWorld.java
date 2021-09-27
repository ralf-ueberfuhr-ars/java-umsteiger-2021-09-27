package de.sample.schulung.demo;

import de.sample.schulung.daten.Person;
import de.sample.schulung.daten.WitzigePerson;

public class HelloWorld {

    // kein const, kein readonly -> nur final
    public static final int COUNT_OF_DAYS = 365;

    public static void main(String[] args) {
        System.out.println("de.sample.schulung.demo.HelloWorld");
        System.out.println("de.sample.schulung.demo.HelloWorld");
        System.out.println("de.sample.schulung.demo.HelloWorld");
        System.out.println("de.sample.schulung.demo.HelloWorld");
        System.out.println("de.sample.schulung.demo.HelloWorld");
        doSomething();
    }

    // Methoden und Variablen in camelCase
    static void doSomething() {
        // Type Aliasing: int = System.Int32 -> in Java NICHT
        // int hat auch keine Methoden -> primitiven Datentypen
        final int countOfThings = 3;
        // Wrappertyp für int
        Integer countOfThings2 = 3; // Autoboxing
        countOfThings2 = Integer.valueOf(3); // ausgeschrieben
        countOfThings2.hashCode();
        //countOfThings2 = null;
        Integer countOfThings3 = countOfThings2; // selbe Referenz
        countOfThings3 = 5; // neue Referenz -> Autoboxing
        // countOfThings3.value = 4; Integer u.a. Wrappertypen sind immutable
        int cot3 = countOfThings2; // AutoUnboxing -> evtl. NPE
        cot3 = countOfThings2.intValue(); // ausgeschrieben -> evtl. NPE
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

        p = new WitzigePerson();

        // Anonyme Klasse
        p = new Person() {
            // Methoden darf ich IMMER überschreiben,
            // AUßER: private, static, final, Konstruktor
            @Override
            public String sayHello() {
                return "Ällerbätsch!";
            }
        };

        p.setName("Tom");
        p.setAge(32);
        int age = p.getAge();
        System.out.println(p.sayHello());
    }

}
