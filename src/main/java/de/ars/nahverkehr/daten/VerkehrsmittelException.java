package de.ars.nahverkehr.daten;

/**
 * Allgemeiner Exception-Typ, der in Verbindung mit Verkehrsmitteln auftreten
 * kann.
 */
public class VerkehrsmittelException extends Exception {

	private static final long serialVersionUID = 1L;

	private final Verkehrsmittel verkehrsmittel;

	public VerkehrsmittelException(Verkehrsmittel verkehrsmittel) {
		super();
		this.verkehrsmittel = verkehrsmittel;
	}

	public Verkehrsmittel getVerkehrsmittel() {
		return verkehrsmittel;
	}

}
