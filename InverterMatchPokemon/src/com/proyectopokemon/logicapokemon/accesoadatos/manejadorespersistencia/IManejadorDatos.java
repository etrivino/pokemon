package com.proyectopokemon.logicapokemon.accesoadatos.manejadorespersistencia;

import java.util.Hashtable;
import java.util.List;

import com.proyectopokemon.logicapokemon.clasespokemon.Pokemon;
import com.proyectopokemon.logicapokemon.clasespokemon.TorneoPokemon;

public interface IManejadorDatos {
	
	public void obtenerPokeInfoPorNombre(Pokemon pokemon);
	
	public void obtenerPokeInfo(List<Pokemon> pokemones);
	
	public Hashtable<String, Pokemon> obtenerTodosLosPokemones();
	
	public void guardarTorneo(TorneoPokemon torneo);
	
}
