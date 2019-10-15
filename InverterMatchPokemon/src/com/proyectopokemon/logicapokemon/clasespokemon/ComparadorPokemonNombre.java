package com.proyectopokemon.logicapokemon.clasespokemon;

import java.util.Comparator;

public class ComparadorPokemonNombre implements Comparator<Pokemon>{
	@Override
	public int compare(Pokemon p1, Pokemon p2) {
		return p1.getNombre().compareTo(p2.getNombre());
	}
}
