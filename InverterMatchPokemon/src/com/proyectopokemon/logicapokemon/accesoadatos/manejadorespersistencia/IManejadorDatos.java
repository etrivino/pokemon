package com.proyectopokemon.logicapokemon.accesoadatos.manejadorespersistencia;

import java.util.List;

import com.proyectopokemon.logicapokemon.pokemon.Pokemon;

public interface IManejadorDatos {
	
	public void obtenerPokeInfoPorNombre(Pokemon pokemon);
	
	public void obtenerPokeInfo(List<Pokemon> pokemones);
	
}
