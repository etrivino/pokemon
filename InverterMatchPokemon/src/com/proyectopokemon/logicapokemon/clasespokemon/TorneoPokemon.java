package com.proyectopokemon.logicapokemon.clasespokemon;

import java.util.List;

public class TorneoPokemon {

	private List<Pokemon> ganadores;
	private int movimientos;
	private String nombre;

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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String descripcion) {
		this.nombre = descripcion;
	}

	public int getMovimientos() {
		return movimientos;
	}

	public void setMovimientos(int movimientos) {
		this.movimientos = movimientos;
	}
	
}
