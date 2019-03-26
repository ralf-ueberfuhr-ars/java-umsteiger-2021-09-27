package de.ars.nahverkehr.daten;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public class Taxi extends Verkehrsmittel {

	private Passagier passagier;

	public Passagier getPassagier() {
		return passagier;
	}

	public void setAktuelleHaltestelle(Haltestelle aktuelleHaltestelle) {
		super.setAktuelleHaltestelle(aktuelleHaltestelle);
	}

	public void einsteigen() {
		if (null != getAktuelleHaltestelle()) {
			Collection<Passagier> wartende = getAktuelleHaltestelle().getWartende();
			for (Iterator<Passagier> it = wartende.iterator(); it.hasNext();) {
				Passagier p = it.next();
				if (null != p) {
					this.passagier = p;
					it.remove();
					System.out.println("\"" + p.getName()
							+ "\" steigt ins Taxi ein.\n - Er möchte bis \""
							+ p.getZiel().getName() + "\" fahren.");
					return;
				}
			}
		}
	}

	@Override
	public void fahre() throws VerkehrsmittelException {
		if (null != passagier) {
			System.out.println("Taxi fährt mit  \"" + passagier.getName()
					+ "\" zur Haltestelle \"" + passagier.getZiel().getName()
					+ "\".");
			super.fahre();
			setAktuelleHaltestelle(passagier.getZiel());
			passagier.setOrt(getAktuelleHaltestelle());
		}
	}

	public void aussteigen() {
		if (null != passagier) {
			System.out.println("\"" + passagier.getName()
					+ "\" steigt an der Haltestelle \""
					+ passagier.getOrt().getName() + "\" aus dem Taxi aus.");
			passagier = null;
		}
	}

	@Override
	public void evakuieren() {
		System.out.println("Evakuiere Taxi!");
		this.passagier = null;
	}

	@Override
	public Iterator<Passagier> iterator() {
		return Arrays.asList(passagier).iterator();
	}

}
