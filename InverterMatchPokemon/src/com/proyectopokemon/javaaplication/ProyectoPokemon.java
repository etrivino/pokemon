package com.proyectopokemon.javaaplication;

import com.proyectopokemon.controlador.ControladorBean;

public class ProyectoPokemon {

	public static void main(String[] args) {
		
		/*
		 * Esta clase main se puede utilizar en caso de que se desee ejecutar la aplicaci�n
		 * como una JavaAplication
		 */
		
		ControladorBean controlador = new ControladorBean();

		controlador.setJsonCad("[ \"Squirtle\", \"Bulbasaur\", \"Charmander\", \"Caterpie\", \"Pidgey\" ]");
		
		controlador.obtenerPasos();
		
		System.out.println("\nCantidad m�nima de movimientos: "+controlador.getCad());

		controlador.setJsonCad("[ \"Squirtle\", \"Bulbasaur\", \"Charmander\", \"Caterpie\", \"Pidgey\" ]");
		
		controlador.obtenerPasos();
		
		System.out.println("\nCantidad m�nima de movimientos: "+controlador.getCad());
		
		
	}

}
