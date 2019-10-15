package com.proyectopokemon.logicapokemon.clasespokemon;

import java.util.List;

public class TorneoPokemon {

	private List<Pokemon> ganadores;

	public TorneoPokemon(List<Pokemon> participantes) {
		super();
		this.setGanadores(participantes);
	}

	public List<Pokemon> getGanadores() {
		return ganadores;
	}

	public void setGanadores(List<Pokemon> ganadores) {
		this.ganadores = ganadores;
	}
	
}
