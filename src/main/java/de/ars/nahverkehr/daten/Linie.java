package de.ars.nahverkehr.daten;

public class Linie {

	private final String name;
	private final Haltestelle[] haltestellen;

	public Linie(String name, Haltestelle... haltestellen) {
		super();
		this.name = name;
		this.haltestellen = haltestellen;
	}

	public String getName() {
		return name;
	}

	public Haltestelle[] getHaltestellen() {
		return haltestellen;
	}

	public Haltestelle getNextHaltestelle(Haltestelle haltestelle,
			Richtung richtung) {
		// .length-1 wegen der Endhaltestelle, die eh keine nächste Haltestelle
		// besitzt
		switch (richtung) {
		case HIN:
			for (int i = 0; i < haltestellen.length - 1; i++) {
				Haltestelle h = haltestellen[i];
				if (h.equals(haltestelle)) {
					return haltestellen[i + 1];
				}
			}
			break;
		case RUECK:
			for (int i = haltestellen.length - 1; i > 0; i--) {
				Haltestelle h = haltestellen[i];
				if (h.equals(haltestelle)) {
					return haltestellen[i - 1];
				}
			}
			break;
		}
		// Randfälle s.o.
		if (!haltestelle.equals(haltestellen[0])
				&& !haltestelle.equals(haltestellen[haltestellen.length - 1])) {
			throw new IllegalArgumentException("Haltestelle \""
					+ haltestelle.getName() + "\" ist nicht Teil der Linie \""
					+ getName() + "\"!");
		}
		return null;
	}

}
