package de.ars.nahverkehr.daten;

import java.util.Collection;
import java.util.Iterator;
import java.util.TreeSet;

public class Haltestelle implements Bemannt {

	private String name;
	private final Collection<Passagier> wartende = new TreeSet<Passagier>();

	public Haltestelle(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Collection<Passagier> getWartende() {
		return wartende;
	}

	@Override
	public void evakuieren() {
		System.out.println("Evakuiere Haltestelle!");
		wartende.clear();
	}

	@Override
	public Iterator<Passagier> iterator() {
		return wartende.iterator();
	}

}
