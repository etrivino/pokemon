package com.proyectopokemon.logicapokemon.accesoadatos.excepciones;

public class ExcepcionPokemonNoExiste extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ExcepcionPokemonNoExiste() {
		super();
	}
	
	public ExcepcionPokemonNoExiste(String mensaje) {
		super(mensaje);
	}

}
