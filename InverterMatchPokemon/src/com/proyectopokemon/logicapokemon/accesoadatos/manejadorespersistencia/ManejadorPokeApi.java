package com.proyectopokemon.logicapokemon.accesoadatos.manejadorespersistencia;

import java.util.Hashtable;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.proyectopokemon.logicapokemon.accesoadatos.modelosmanejadores.RespuestaInfoPokemon;
import com.proyectopokemon.logicapokemon.accesoadatos.modelosmanejadores.RespuestaPokemon;
import com.proyectopokemon.logicapokemon.accesoadatos.modelosmanejadores.RespuestaTodosLosPokemones;
import com.proyectopokemon.logicapokemon.clasespokemon.Pokemon;
import com.proyectopokemon.logicapokemon.clasespokemon.TorneoPokemon;

public class ManejadorPokeApi implements IManejadorDatos{
	
	//Constantes privadas
	private final String URL_TODOS_LOS_POKEMONES = "https://pokeapi.co/api/v2/pokemon/?offset=0&limit=2000";
	private final String URL_POKEMONE = "https://pokeapi.co/api/v2/pokemon/";
	private final String EXREGID = "https:\\/\\/pokeapi\\.co\\/api\\/v2\\/pokemon\\/([0-9]+)\\/";
	
	
	@Override
	public RespuestaInfoPokemon obtenerPokeInfoPorNombre(RespuestaInfoPokemon pokemon) {
		
		HttpResponse<String> response = null;
		try {
			response = Unirest.get(URL_POKEMONE+pokemon.getName()).asString();
		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pokemon = new Gson().fromJson(response.getBody(), RespuestaInfoPokemon.class);
		return pokemon;
	}

	@Override
	public void obtenerPokeInfo(List<Pokemon> pokemones) {
		// TODO Auto-generated method stub
		
	}
	
	private RespuestaTodosLosPokemones obtenerPokemonesDeApi() {

		RespuestaTodosLosPokemones pokemones = null;
		
		try {
			
			HttpResponse<String> response = Unirest.get(URL_TODOS_LOS_POKEMONES).asString();
			pokemones = new Gson().fromJson(response.getBody(), RespuestaTodosLosPokemones.class);
			
		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return pokemones;
		
	}
	
	@Override
	public Hashtable<String, Pokemon> obtenerTodosLosPokemones() {
		
		//Crea la tabla a retornar
		Hashtable<String, Pokemon> lista = new Hashtable<String, Pokemon>();
		
		RespuestaTodosLosPokemones pokemones = obtenerPokemonesDeApi();
		
		//Si se obtuvieron pokemones
		if(pokemones.getResults().size() > 0) {
			
			String idCadena = null;
			int id = 0;
			
			//Añade los pokemones a la HashTable
			for(RespuestaPokemon respuesta: pokemones.getResults()) {
				
				Pattern expresion = Pattern.compile(EXREGID);
				Matcher matcher = expresion.matcher(respuesta.getUrl());
				
				if(matcher.find()) {
					idCadena = matcher.group(1);
				}else {
					idCadena = null;
				}
				
				id = Integer.parseInt(idCadena.toString());
				
				lista.put(respuesta.getName(), new Pokemon(id, respuesta.getName()));
				
			}
			
		}
		
		return lista;
	}

	@Override
	public void guardarTorneo(TorneoPokemon torneo) {
		//No guarda ya que este manejador es de solo lectura
		
	}

}
