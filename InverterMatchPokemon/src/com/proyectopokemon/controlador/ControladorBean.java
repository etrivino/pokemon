package com.proyectopokemon.controlador;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.proyectopokemon.logicapokemon.accesoadatos.GestorPokeinfo;
import com.proyectopokemon.logicapokemon.accesoadatos.IGestorPokeinfo;
import com.proyectopokemon.logicapokemon.accesoadatos.excepciones.ExcepcionPokemonNoExiste;
import com.proyectopokemon.logicapokemon.accesoadatos.modelosmanejadores.RespuestaInfoPokemon;
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
	private String nombreTorneo;
	private String cad;
	private String mensajeDeError;
	private String mensajeErrorNombre;
	private int cantidadDePasos = 0;
	private String pokemonABuscar;
	private List<RespuestaInfoPokemon> listaPokemonActual = new ArrayList<RespuestaInfoPokemon>();
				
	private List<TorneoPokemon> torneos = new ArrayList<TorneoPokemon>();

    public void validarNombreTorneo(AjaxBehaviorEvent evento) {
        if (nombreTorneo.length() < 4) {
            setMensajeErrorNombre("El nombre tiene que tener como minimo 4 caracteres");
        } else {
            if (nombreTorneo.length() > 10) {
                setMensajeErrorNombre("El nombre puede tener como maximo 10 caracteres");
            } else {
                setMensajeErrorNombre("");
            }
        }
    }
    
	public void obtenerPasos() {
		
		//Crea la lista de pokemones a partir del JSON
		Gson gson = new Gson();
		
		Type tipo = new TypeToken<List<String>>(){}.getType();
		List<String> lista = gson.fromJson(jsonCad, tipo );
		if(lista.size() == 0) {
			mensajeDeError = "Por favor verifique el JSON!";
			cad = "Por favor verifique el JSON!";
			cantidadDePasos = 0;
			return;
		}
		
		//Crea el torneo
		TorneoPokemon torneoPokemon = new TorneoPokemon(stringArrAPokemonArr(lista));
		torneoPokemon.setNombre(nombreTorneo);
		
		IGestorPokeinfo gestorInfo = GestorPokeinfo.getInstance();
		try {
			
			//Obtiene la información faltante de cada pokemon
			gestorInfo.obtenerPokeInfo(torneoPokemon.getGanadores());

			//Invoca al analista del torneo para que indique la cantidad de pasos
			IAnalistaTorneo analista = AnalistaGeneral.getInstance();
			cantidadDePasos=0;
			//Solicita la cantidad de pasos al analista
			cantidadDePasos = analista.calcularMinimosMovimientos(torneoPokemon);
			
			//Establece el resultado
			if(cantidadDePasos == -1)
				cad = "El torneo ha sido saboteado!";
			else
				cad = cantidadDePasos+"";
			
			//Almacena el torneo en la base de datos
			torneoPokemon.setMovimientos(cantidadDePasos);
			torneos.add(torneoPokemon);
			
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
	
	public void obtenerPokeInfoPorNombre() {
		
		listaPokemonActual.clear();
		
		RespuestaInfoPokemon poke = new RespuestaInfoPokemon();
		
		poke.setName(pokemonABuscar.toLowerCase());
		
		IGestorPokeinfo gestorInfo = GestorPokeinfo.getInstance();
		poke = gestorInfo.obtenerPokeInfoPorNombre(poke);
		
		listaPokemonActual.add(poke);
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

	public String getNombreTorneo() {
		return nombreTorneo;
	}

	public void setNombreTorneo(String nombreTorneo) {
		this.nombreTorneo = nombreTorneo;
	}
	public String getMensajeErrorNombre() {
		return mensajeErrorNombre;
	}
	public void setMensajeErrorNombre(String mensajeErrorNombre) {
		this.mensajeErrorNombre = mensajeErrorNombre;
	}

	public List<TorneoPokemon> getTorneos() {
		return torneos;
	}

	public void setTorneos(List<TorneoPokemon> torneos) {
		this.torneos = torneos;
	}

	public String getMensajeDeError() {
		return mensajeDeError;
	}

	public void setMensajeDeError(String mensajeDeError) {
		this.mensajeDeError = mensajeDeError;
	}

	public String getPokemonABuscar() {
		return pokemonABuscar;
	}

	public void setPokemonABuscar(String pokemonABuscar) {
		this.pokemonABuscar = pokemonABuscar;
	}

	public List<RespuestaInfoPokemon> getListaPokemonActual() {
		return listaPokemonActual;
	}

	public void setListaPokemonActual(List<RespuestaInfoPokemon> listaPokemonActual) {
		this.listaPokemonActual = listaPokemonActual;
	}
	
}
