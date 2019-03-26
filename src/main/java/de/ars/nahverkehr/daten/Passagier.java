package de.ars.nahverkehr.daten;

public class Passagier implements Comparable<Passagier> {

	private final String name;
	private final Haltestelle ziel;
	private Geduld geduld = Geduld.NEUTRAL;
	private Haltestelle ort;

	public Passagier(String name, Haltestelle ziel) {
		super();
		this.name = name;
		this.ziel = ziel;
	}

	public String getName() {
		return name;
	}

	public Haltestelle getOrt() {
		return ort;
	}

	public void setOrt(Haltestelle ort) {
		this.ort = ort;
	}

	public Haltestelle getZiel() {
		return ziel;
	}

	public Geduld getGeduld() {
		return geduld;
	}

	public void setGeduld(Geduld geduld) {
		this.geduld = geduld;
	}

	@Override
	public int compareTo(Passagier o) {
		if(this.geduld == o.geduld) {
			return 1;
		} else {
			return this.geduld.compareTo(o.geduld);
		}
	}

}
