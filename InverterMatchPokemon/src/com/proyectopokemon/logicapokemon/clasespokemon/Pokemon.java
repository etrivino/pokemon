/**
 * 
 */
package com.proyectopokemon.logicapokemon.clasespokemon;

import java.io.Serializable;

/**
 * @author EstebanTriviño
 *
 */
public class Pokemon implements Serializable{

	//Atributos básicos del pokemon
	private int pokedexcodigo = 0;
	private String nombre;
	
	//Cantidad de retos disponibles
	private	int RetosDisponibles = 2;
	
	//Constructores
	public Pokemon() {
		super();
	}
	
	
	public Pokemon(String nombre) {
		super();
		this.nombre = nombre;
	}

	public Pokemon(int pokedexcodigo, String nombre) {
		super();
		this.pokedexcodigo = pokedexcodigo;
		this.nombre = nombre;
	}
	
	//Métodos creados
	public void disminuirReto() {
		
		this.RetosDisponibles--;
		
	}

	//Analizadores y modificadores
	public int getRetosDisponibles() {
		return RetosDisponibles;
	}

	public void setRetosDisponibles(int retosDisponibles) {
		RetosDisponibles = retosDisponibles;
	}

	public int getPokedexcodigo() {
		return pokedexcodigo;
	}
	
	public void setPokedexcodigo(int pokedexcodigo) {
		this.pokedexcodigo = pokedexcodigo;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
