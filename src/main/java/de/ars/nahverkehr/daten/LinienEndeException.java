package de.ars.nahverkehr.daten;

public class LinienEndeException extends VerkehrsmittelException {

	private static final long serialVersionUID = 1L;

	private final Linie linie;

	public LinienEndeException(Verkehrsmittel verkehrsmittel, Linie linie) {
		super(verkehrsmittel);
		this.linie = linie;
	}

	public Linie getLinie() {
		return linie;
	}

}
