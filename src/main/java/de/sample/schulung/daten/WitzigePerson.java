package de.sample.schulung.daten;

public class WitzigePerson extends Person {

    @Override
    public String sayHello() {
        return super.sayHello() + " der Witzige";
    }
}
