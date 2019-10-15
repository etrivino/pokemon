package com.proyectopokemon.logicapokemon.accesoadatos;

import java.util.List;

import com.proyectopokemon.logicapokemon.clasespokemon.Pokemon;

public interface IGestorPokeinfo {
	
	public void obtenerPokeInfo(List<Pokemon> pokemones);
	
	public void obtenerPokeInfoPorNombre(Pokemon pokemon);
	
}
