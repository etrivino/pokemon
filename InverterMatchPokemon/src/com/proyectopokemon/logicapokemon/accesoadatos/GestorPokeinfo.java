package com.proyectopokemon.logicapokemon.accesoadatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import com.proyectopokemon.logicapokemon.accesoadatos.manejadorespersistencia.FabricaDeManejadores;
import com.proyectopokemon.logicapokemon.accesoadatos.manejadorespersistencia.IManejadorDatos;
import com.proyectopokemon.logicapokemon.accesoadatos.manejadorespersistencia.ManejadorMySql;
import com.proyectopokemon.logicapokemon.accesoadatos.manejadorespersistencia.TipoDeManejador;
import com.proyectopokemon.logicapokemon.clasespokemon.Pokemon;

/**
 * @author EstebanTrivi�o
 *
 */
public class GestorPokeinfo implements IGestorPokeinfo{
	
	/*
	 * Se utiliza el patr�n Singleton para garantizar que solo se pueda acceder a la informaci�n de la base de datos a trav�s
	 * de la �nica instancia
	 */
	
	//Variables
	Hashtable<String, Pokemon> memoria = new Hashtable<String, Pokemon>();
	
	//Instancia �nica de la clase
	private static GestorPokeinfo gestorInfo = new GestorPokeinfo();
	
	
	//Constructor privado
	private GestorPokeinfo() {
		// TODO Auto-generated constructor stub
		//memoria.put("Bulbasaur", new Pokemon(1, "Bulbasaur"));
	}
	
	//M�todo para acceder a la instancia
	public static GestorPokeinfo getInstance() {
		return gestorInfo;
	}
	
	public void obtenerPokeInfo(List<Pokemon> pokemones) {
		
		List<Pokemon> aConsultar = new ArrayList<Pokemon>();
		
		//Verifica si la informaci�n del pokemon est� en cache
		for(Pokemon pokemon: pokemones) {
			
			//Si est� en memoria
			if(memoria.containsKey(pokemon.getNombre())) {
				
				System.out.println("En memoria!");
				
				//Asigna el codigo pokedex en memoria  
				pokemon.setPokedexcodigo(memoria.get(pokemon.getNombre()).getPokedexcodigo());
				
			}else {

				System.out.println("A consultar!");
				
				//Almacena el pokemon en la lista para consultar
				aConsultar.add(pokemon);
				
			}
			
		}
		
		//si hay pokemones a consultar
		if(aConsultar.size() > 0) {
			
			//Inicializa el manejardor de persistencia
			IManejadorDatos manejador = FabricaDeManejadores.crearManejador(TipoDeManejador.MYSQL);
			
			//Realiza la consulta de todos los pokemones de la lista
			manejador.obtenerPokeInfo(aConsultar);
			
			//Agrega los pokemones en memoria
			for(Pokemon pokemon: aConsultar) {
				memoria.put(pokemon.getNombre(), pokemon);
				
				//Agrega la informaci�n faltante
				for(Pokemon p: pokemones) {
					if(p.getNombre().equals(pokemon.getNombre())) {
						p.setPokedexcodigo(pokemon.getPokedexcodigo());
					}
				}
				
			}
			
		}
		
	}
	
	public void obtenerPokeInfoPorNombre(Pokemon pokemon) {
		// TODO Auto-generated method stub
		
	}
	
}
