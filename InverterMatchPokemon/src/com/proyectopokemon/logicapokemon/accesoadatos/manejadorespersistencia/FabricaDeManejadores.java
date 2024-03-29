/**
 * 
 */
package com.proyectopokemon.logicapokemon.accesoadatos.manejadorespersistencia;

/**
 * @author EstebanTriviņo
 *
 */
public class FabricaDeManejadores {
	
	public static IManejadorDatos crearManejador(TipoDeManejador tipo) {
		
		IManejadorDatos manejador = null;
		
		//Verifica el tipo de manejador a crear
		switch(tipo) {
		
		case MYSQL:
			manejador = new ManejadorMySql();
			break;
			
		case POKEAPI:
			manejador = new ManejadorPokeApi();
			break;
			
		default:
			manejador = new ManejadorMySql();
		}
		
		return manejador;
		
	}
	
}
