package com.proyectopokemon.logicapokemon.pokemon;

public class ComparadorPokemonNombre implements Comparator<Pokemon>{
	@Override
	public int compare(Pokemon p1, Pokemon p2) {
		return p1.getNombre().compareTo(p2.getNombre());
	}
}
