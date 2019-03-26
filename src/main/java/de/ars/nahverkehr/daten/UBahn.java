package de.ars.nahverkehr.daten;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;

public class UBahn extends Verkehrsmittel {

	private static final int STANDARD_KAPAZITAET = 100;

	private Linie linie;
	private Richtung richtung = Richtung.HIN;
	private final int kapazitaet;
	private final Passagier[] passagiere;
	private boolean umkehrbar = true;

	public UBahn() {
		this(UBahn.STANDARD_KAPAZITAET);
	}

	public UBahn(int kapazitaet) {
		this.kapazitaet = kapazitaet;
		this.passagiere = new Passagier[kapazitaet];
	}

	public Richtung getRichtung() {
		return richtung;
	}

	public Linie getLinie() {
		return linie;
	}

	public void setLinie(Linie linie) {
		this.linie = linie;
		setAktuelleHaltestelle(linie.getHaltestellen()[0]);
	}

	public int getKapazitaet() {
		return kapazitaet;
	}

	public Passagier[] getPassagiere() {
		return passagiere;
	}

	public void fahre() throws VerkehrsmittelException {
		ausrichten();
		Haltestelle nextHaltestelle = getLinie().getNextHaltestelle(getAktuelleHaltestelle(), getRichtung());
		if (null == nextHaltestelle) {
			this.richtung = getRichtung() == Richtung.HIN ? Richtung.RUECK : Richtung.HIN;
			System.out.println("U-Bahn wechselt die Richtung, fährt jetzt " + getRichtung());
			nextHaltestelle = getLinie().getNextHaltestelle(getAktuelleHaltestelle(), getRichtung());
		}
		System.out.println("U-Bahn " + getLinie().getName() + " fährt weiter zu Haltestelle \""
				+ nextHaltestelle.getName() + "\"");
		super.fahre();
		setAktuelleHaltestelle(nextHaltestelle);
		Arrays.asList(this.passagiere)
				.forEach(p -> Optional.ofNullable(p).ifPresent(np -> np.setOrt(getAktuelleHaltestelle())));
	}

	private void ausrichten() throws LinienEndeException {
		Haltestelle nextHaltestelle = getLinie().getNextHaltestelle(getAktuelleHaltestelle(), getRichtung());
		if (null == nextHaltestelle) {
			if (isUmkehrbar()) {
				this.richtung = getRichtung() == Richtung.HIN ? Richtung.RUECK : Richtung.HIN;
				System.out.println(
						"U-Bahn \"" + getLinie().getName() + "\" wechselt die Richtung, fährt jetzt " + getRichtung());
			} else {
				throw new LinienEndeException(this, getLinie());
			}
		}
	}

	private boolean isPassendFuer(Passagier p) {
		try {
			ausrichten();
		} catch (LinienEndeException e) {
			return false;
		}
		Haltestelle ziel = p.getZiel();
		Haltestelle[] haltestellen = getLinie().getHaltestellen();
		boolean aktuelleVorbei = false;
		switch (getRichtung()) {
		case HIN:
			for (int i = 0; i < haltestellen.length; i++) {
				Haltestelle h = haltestellen[i];
				if (aktuelleVorbei) {
					if (h.equals(ziel)) {
						return true;
					}
				} else {
					if (h.equals(getAktuelleHaltestelle())) {
						aktuelleVorbei = true;
					}
				}
			}
			break;
		case RUECK:
			for (int i = haltestellen.length - 1; i >= 0; i--) {
				Haltestelle h = haltestellen[i];
				if (aktuelleVorbei) {
					if (h.equals(ziel)) {
						return true;
					}
				} else {
					if (h.equals(getAktuelleHaltestelle())) {
						aktuelleVorbei = true;
					}
				}
			}
			break;
		}
		return false;
	}

	private boolean nimmPlatz(Passagier p) {
		for (int i = 0; i < this.passagiere.length; i++) {
			Passagier platz = this.passagiere[i];
			if (null == platz) {
				this.passagiere[i] = p;
				System.out.println("Passagier \"" + p.getName() + "\" steigt in die \"" + getLinie().getName()
						+ "\" ein.\n - Er möchte bis \"" + p.getZiel().getName() + "\" fahren.");
				return true;
			}
		}
		return false;
	}

	public void einsteigen() throws LinienEndeException {
		Collection<Passagier> wartende = getAktuelleHaltestelle().getWartende();
		wartende.removeIf(p -> null != p && isPassendFuer(p) && nimmPlatz(p));
	}

	public void aussteigen() {
		for (int i = 0; i < passagiere.length; i++) {
			Passagier p = passagiere[i];
			if (null != p && p.getZiel().equals(getAktuelleHaltestelle())) {
				System.out.println(
						"Passagier \"" + p.getName() + "\" steigt aus der \"" + getLinie().getName() + "\" aus.");
				passagiere[i] = null;
			}

		}
	}

	@Override
	public void evakuieren() {
		System.out.println("Evakuiere UBahn!");
		for (int i = 0; i < passagiere.length; i++) {
			passagiere[i] = null;
		}
	}

	@Override
	public Iterator<Passagier> iterator() {
		// Remove null values
		final Set<Passagier> set = new HashSet<Passagier>();
		set.addAll(Arrays.asList(passagiere));
		set.remove(null);
		return set.iterator();
	}

	public boolean isUmkehrbar() {
		return umkehrbar;
	}

	public void setUmkehrbar(boolean umkehrbar) {
		this.umkehrbar = umkehrbar;
	}

}
