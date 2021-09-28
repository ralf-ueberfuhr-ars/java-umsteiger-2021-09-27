package de.sample.schulung.demo;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeSet;

public class CollectionsDemo {

    public static void main(String[] args) {

        Collection<String> strings = new LinkedList<>();
        strings = new HashSet<>();
        strings = new TreeSet<>(); // alphabetisch sortiert; String implements Comparable
        strings = new TreeSet<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int result = Integer.valueOf(o1.length()).compareTo(o2.length());
                return result != 0 ? result : o1.compareTo(o2);
            }
        });
        strings.add("xasda");
        strings.add("a");
        strings.add("edfg");
        strings.add("b");
        strings.add("zdfgsa");
        strings.add("a");
        strings.add("_");
        System.out.println(strings);
        // Iterieren
        for (String t: strings) {
            System.out.println(t);
        }
        for (Iterator<String> it = strings.iterator(); it.hasNext(); ) {
            String t = it.next();
            System.out.println(t);
            if(t.length()<3) {
                it.remove(); // KEIN strings.remove(t);
            }
        }
        System.out.println(strings);

        // nur Referenztypen als Typparameter (Generics)
        Map<String, Integer> artikelAufLager = new HashMap<>();
        artikelAufLager.put("158A", 56);
        artikelAufLager.put("F48A", 12);
        System.out.println(artikelAufLager);
        int anzahl = artikelAufLager.get("F48A"); // Vorsicht, evtl. NPE -> Optional?

        // Raw Types, keine Vererbung sondern Geschwisterklassen
        Collection<Object> objects = new HashSet<>();
        Collection<String> stringObjects = new HashSet<>();
        //objects = stringObjects;
        // Oberklasse für beide
        // Collection col = objects;

        Collection<Integer> numbers = new LinkedList<>(Arrays.asList(3, 5, 8, -3));
        Collection col = numbers;
        // col.add("ätsch!"); -> das gibt in der nächsten Zeile eine ClassCastException
        for (Integer i: numbers) {

        }
        Collection<Integer> unmodifiables = Collections.unmodifiableCollection(numbers);

    }

}
