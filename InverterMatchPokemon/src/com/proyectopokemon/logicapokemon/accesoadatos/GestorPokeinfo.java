package com.proyectopokemon.logicapokemon.accesoadatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import com.proyectopokemon.logicapokemon.accesoadatos.excepciones.ExcepcionPokemonNoExiste;
import com.proyectopokemon.logicapokemon.accesoadatos.manejadorespersistencia.FabricaDeManejadores;
import com.proyectopokemon.logicapokemon.accesoadatos.manejadorespersistencia.IManejadorDatos;
import com.proyectopokemon.logicapokemon.accesoadatos.manejadorespersistencia.ManejadorMySql;
import com.proyectopokemon.logicapokemon.accesoadatos.manejadorespersistencia.TipoDeManejador;
import com.proyectopokemon.logicapokemon.clasespokemon.Pokemon;

/**
 * @author EstebanTriviño
 *
 */
public class GestorPokeinfo implements IGestorPokeinfo{
	
	/*
	 * Se utiliza el patrón Singleton para garantizar que solo se pueda acceder a la información de la base de datos a través
	 * de la única instancia
	 */
	
	//Variables
	Hashtable<String, Pokemon> memoria = new Hashtable<String, Pokemon>();
	
	//Instancia única de la clase
	private static GestorPokeinfo gestorInfo = new GestorPokeinfo();
	
	
	//Constructor privado
	private GestorPokeinfo() {
		// TODO Auto-generated constructor stub
		//memoria.put("Bulbasaur", new Pokemon(1, "Bulbasaur"));
	}
	
	//Método para acceder a la instancia
	public static GestorPokeinfo getInstance() {
		return gestorInfo;
	}
	
	public void obtenerPokeInfo(List<Pokemon> pokemones) throws ExcepcionPokemonNoExiste {
		
		List<Pokemon> aConsultar = new ArrayList<Pokemon>();
		
		//Verifica si la información del pokemon está en cache
		for(Pokemon pokemon: pokemones) {
			
			//Si está en memoria
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
				
				//Agrega la información faltante
				for(Pokemon p: pokemones) {
					if(p.getNombre().equals(pokemon.getNombre())) {
						p.setPokedexcodigo(pokemon.getPokedexcodigo());
					}
				}
				
				
			}
			

			//Verifica si algún pokemon no existe
			for(Pokemon p: pokemones) {
				System.out.println(p.getNombre()+": "+p.getPokedexcodigo());
				
				if(p.getPokedexcodigo() == 0) {
					throw new ExcepcionPokemonNoExiste("El pokemon "+p.getNombre()+" no existe!");
				}
			}
			
		}
		
	}
	
	public void obtenerPokeInfoPorNombre(Pokemon pokemon) {
		// TODO Auto-generated method stub
		
	}
	
}
