package de.sample.schulung.demo;

import java.io.IOException;
import java.util.Objects;

public class ExceptionDemo {

    /**
     * Macht einen Dateisystemzugriff.
     * @param fileName Dateiname, darf nicht null sein.
     * @throws IOException wenn Datei nicht erreichbar ist
     * @throws IllegalArgumentException wenn der Dateiname null ist
     */
    private static void doFileAccess(String fileName) throws IOException {
        if(null == fileName) {
            // unchecked, weil RuntimeException
            throw new IllegalArgumentException("fileName must not be null");
        }
        // Alternative für Null-Prüfung
        Objects.requireNonNull(fileName);
        // Dateisystemzugriff
        // evtl. Fehler beim Zugriff
        throw new IOException("geht nicht"); // Checked Exception
    }

    public static void main(String[] args) {
        try {
            doFileAccess("test");
        } catch (IOException e) {
            e.printStackTrace();
        }
        // DON'T
        try {
            doFileAccess("test");
        } catch (Throwable e) {
            e.printStackTrace();
        }
        // DO
        try {
            doFileAccess("test");
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Multi-Catch
        try {
            doFileAccess("test");
        } catch (IOException | IllegalArgumentException e) {
            e.printStackTrace();
        }

    }
}
