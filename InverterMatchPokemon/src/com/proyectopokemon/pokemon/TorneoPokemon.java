package com.proyectopokemon.pokemon;

import java.util.List;

public class TorneoPokemon {
	
	List<Pokemon> participantes;

	public TorneoPokemon(List<Pokemon> participantes) {
		super();
		this.participantes = participantes;
	}

	public List<Pokemon> getParticipantes() {
		return participantes;
	}

	public void setParticipantes(List<Pokemon> participantes) {
		this.participantes = participantes;
	}
	
}
