package de.ars.nahverkehr.daten;

public abstract class Verkehrsmittel implements Bemannt {

	private Haltestelle aktuelleHaltestelle;

	public Haltestelle getAktuelleHaltestelle() {
		return aktuelleHaltestelle;
	}

	protected void setAktuelleHaltestelle(Haltestelle aktuelleHaltestelle) {
		this.aktuelleHaltestelle = aktuelleHaltestelle;
	}
	
	public abstract void einsteigen() throws VerkehrsmittelException;
	public void fahre() throws VerkehrsmittelException {
		System.out.println("Brumm! Huup!");
	}
	public abstract void aussteigen() throws VerkehrsmittelException;

}
