package de.ars.nahverkehr.start;

import java.util.Arrays;

import de.ars.nahverkehr.daten.Geduld;
import de.ars.nahverkehr.daten.Haltestelle;
import de.ars.nahverkehr.daten.Linie;
import de.ars.nahverkehr.daten.Passagier;
import de.ars.nahverkehr.daten.Richtung;
import de.ars.nahverkehr.daten.Taxi;
import de.ars.nahverkehr.daten.UBahn;
import de.ars.nahverkehr.daten.Verkehrsmittel;
import de.ars.nahverkehr.daten.VerkehrsmittelException;

public class Start {

	public static void main(String[] args) {

		// Haltestellen
		Haltestelle h1 = new Haltestelle("Carolaplatz");
		System.out.println("Haltestelle angelegt: " + h1);
		Haltestelle h2 = new Haltestelle("Albertplatz");
		System.out.println("Haltestelle angelegt: " + h2);
		Haltestelle h3 = new Haltestelle("Altmarkt");
		System.out.println("Haltestelle angelegt: " + h3);
		Haltestelle h4 = new Haltestelle("Reisewitzer Straße");
		System.out.println("Haltestelle angelegt: " + h4);
		// Doppelter Bezeichner
		Haltestelle h1Doppelt = new Haltestelle(new String("Carolaplatz"));
		// Falsch, je nach JVM
		System.out.println(h1.getName() == h1Doppelt.getName());
		// So auf jeden Fall korrekt
		System.out.println(h1.getName().equals(h1Doppelt.getName()));

		// Linien
		Linie linie1 = new Linie("U1", h1, h2, h3);
		System.out.println("Linie angelegt: " + linie1);
		Linie linie2 = new Linie("U2", h1, h4, h3);
		System.out.println("Linie angelegt: " + linie2);

		// UBahnen
		UBahn u1 = new UBahn();
		u1.setLinie(linie1);
		System.out.println("U-Bahn angelegt: " + u1);
		UBahn u2 = new UBahn(250);
		u2.setLinie(linie2);
		System.out.println("U-Bahn angelegt: " + u2);
		UBahn u3 = new UBahn(250);
		u3.setUmkehrbar(false);
		u3.setLinie(linie2);
		System.out.println("U-Bahn angelegt: " + u3);

		// Taxis
		Taxi t1 = new Taxi();
		t1.setAktuelleHaltestelle(h1);

		// Passagiere
		Passagier p1 = new Passagier("Michael Müller", h3);
		System.out.println("Passagier angelegt: " + p1);
		Passagier p2 = new Passagier("Thomas Schmidt", h4);
		System.out.println("Passagier angelegt: " + p2);
		Passagier p3 = new Passagier("Manfred Rück", h1);
		System.out.println("Passagier angelegt: " + p3);
		Passagier p4 = new Passagier("Thomas Schmidt ungeduldiger Bruder", h4);
		p4.setGeduld(Geduld.UNGEDULDIG);
		System.out.println("Passagier angelegt: " + p4);
		Passagier p5 = new Passagier("John No-Line", h4);
		System.out.println("Passagier angelegt: " + p5);

		// Zuweisung an Haltestelle
		p1.setOrt(h1);
		p2.setOrt(h1);
		p4.setOrt(h1);
		h1.getWartende().addAll(Arrays.asList(p1, p2, p4));
		p3.setOrt(h3);
		h1.getWartende().addAll(Arrays.asList(p3));
		p5.setOrt(h2);
		h2.getWartende().addAll(Arrays.asList(p5));
		
		System.out.println("------------ FERTIG ----------------");
		System.out.println();
		Start.gruppenFahren(t1);
		Start.gruppenFahren(u1,u1,u1,u1,u1,u1);
		Start.gruppenFahren(u2,u2);
		Start.gruppenFahren(u3,u3, /*LinienEndeException*/ u3);
		System.out.println("------------ FERTIG ----------------");
		h2.forEach(p->System.out.println("Passagier \"" + p.getName() + "\" wartet noch an Haltestelle \"" + h2.getName() + "\""));
		// IllegalArgumentException
		System.out.println(linie1.getNextHaltestelle(h4, Richtung.HIN));

	}

	private static void fahre(Verkehrsmittel v) throws VerkehrsmittelException {
		v.einsteigen();
		v.fahre();
		v.aussteigen();
	}

	private static void fahreNoEx(Verkehrsmittel v) {
		try {
			Start.fahre(v);
		} catch (VerkehrsmittelException e) {
			throw new RuntimeException(e);
		}
	}

	private static void gruppenFahren(Verkehrsmittel... verkehrsmittel) {
		try {
			Arrays.asList(verkehrsmittel).forEach(Start::fahreNoEx);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
