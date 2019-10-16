package com.proyectopokemon.javaaplication;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.proyectopokemon.controlador.ControladorBean;
import com.proyectopokemon.logicapokemon.accesoadatos.manejadorespersistencia.FabricaDeManejadores;
import com.proyectopokemon.logicapokemon.accesoadatos.manejadorespersistencia.IManejadorDatos;
import com.proyectopokemon.logicapokemon.accesoadatos.manejadorespersistencia.TipoDeManejador;

public class ProyectoPokemon {

	private final static String EXREGID = "https:\\/\\/pokeapi.co\\/api\\/v2\\/pokemon\\/";
	
	public static void main(String[] args) {
		
		/*
		 * Esta clase main se puede utilizar en caso de que se desee ejecutar la aplicación
		 * como una JavaAplication
		 */
		
		ControladorBean controlador = new ControladorBean();

		controlador.setJsonCad("[ \"Squirtle\", \"Bulbasaur\", \"Charmander\", \"Caterpie\", \"Pidgey\" ]");
		
		controlador.obtenerPasos();
		
		System.out.println("\nCantidad mínima de movimientos: "+controlador.getCad());
		
	}

}
