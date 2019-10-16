/**
 * 
 */
package com.proyectopokemon.logicapokemon.accesoadatos.modelosmanejadores;

import java.util.ArrayList;
import java.util.List;

/**
 * @author EstebanTrivino
 *
 */
public class RespuestaTodosLosPokemones {
	
	private List<RespuestaPokemon> results = new ArrayList<RespuestaPokemon>();
	
	public RespuestaTodosLosPokemones() {
		
	}

	public List<RespuestaPokemon> getResults() {
		return results;
	}

	public void setResults(List<RespuestaPokemon> results) {
		this.results = results;
	}
	
}
