package com.proyectopokemon.controlador;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.proyectopokemon.logicapokemon.accesoadatos.GestorPokeinfo;
import com.proyectopokemon.logicapokemon.accesoadatos.IGestorPokeinfo;
import com.proyectopokemon.logicapokemon.accesoadatos.excepciones.ExcepcionPokemonNoExiste;
import com.proyectopokemon.logicapokemon.analistadetorneopokemon.AnalistaGeneral;
import com.proyectopokemon.logicapokemon.analistadetorneopokemon.IAnalistaTorneo;
import com.proyectopokemon.logicapokemon.clasespokemon.Pokemon;
import com.proyectopokemon.logicapokemon.clasespokemon.TorneoPokemon;

@ManagedBean(name = "controlador")
@SessionScoped
public class ControladorBean implements Serializable{
	
	public ControladorBean() {
		
	}
	
	//Variables
	//Se establece el caso básico para que salga cargado en pantalla
	private String jsonCad = "[ \"Squirtle\", \"Bulbasaur\", \"Charmander\", \"Caterpie\", \"Pidgey\" ]";
	private String cad;
	private int cantidadDePasos = 0;
	
	public void obtenerPasos() {
		
		//Crea la lista de pokemones a partir del JSON
		Gson gson = new Gson();
		
		Type tipo = new TypeToken<List<String>>(){}.getType();
		List<String> lista = gson.fromJson(jsonCad, tipo );
		
		//Crea el torneo
		TorneoPokemon torneoPokemon = new TorneoPokemon(stringArrAPokemonArr(lista));
		
		IGestorPokeinfo gestorInfo = GestorPokeinfo.getInstance();
		try {
			
			//Obtiene la información faltante de cada pokemon
			gestorInfo.obtenerPokeInfo(torneoPokemon.getGanadores());

			//Invoca al analista del torneo para que indique la cantidad de pasos
			IAnalistaTorneo analista = AnalistaGeneral.getInstance();
			
			//Solicita la cantidad de pasos al analista
			cantidadDePasos = analista.calcularMinimosMovimientos(torneoPokemon);
			
			//Establece el resultado
			if(cantidadDePasos == -1)
				cad = "El torneo ha sido saboteado!";
			else
				cad = cantidadDePasos+"";
			
			//Almacena el torneo en la base de datos
			
			
		} catch (ExcepcionPokemonNoExiste e) {
			
			cad = e.getMessage();
			
		}
		
	}
	
	private List<Pokemon> stringArrAPokemonArr(List<String> lista){
		
		//Crea la lista a retornar
		List<Pokemon> ganadores = new ArrayList<Pokemon>();
		
		//Itera sobre la lista de String para añadir los pokemons a la lista
		for(String nombre: lista) {
			
			ganadores.add(new Pokemon(nombre.toLowerCase()));
			
		}
		
		return ganadores;
		
	}

	public String getJsonCad() {
		return jsonCad;
	}

	public void setJsonCad(String jsonCad) {
		this.jsonCad = jsonCad;
	}
	
	public String getCad() {
		return cad;
	}

	public void setCad(String cad) {
		this.cad = cad;
	}
	
}
