package de.sample.schulung.demo;

import java.lang.reflect.Method;

public class AnnotationSample {

    @Description("eine Beispiel-Methode")
    private static void annotierteMethode() {

    }

    private static void nichtAnnotierteMethode() {

    }

    public static void main(String[] args) {
        Method[] methods = AnnotationSample.class.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println(method.getName());
            Description desc = method.getAnnotation(Description.class);
            if(desc != null) {
                System.out.printf("Methode '%s' - '%s'", method.getName(), desc.value());
            }
        }
    }

}
