package com.proyectopokemon.analistaDeTorneoPokemon;

import java.util.List;

import com.proyectopokemon.pokemon.Pokemon;
import com.proyectopokemon.pokemon.TorneoPokemon;

public class AnalistaGeneral implements IAnalistaTorneo{

	@Override
	public int calcularMinimosMovimientos(TorneoPokemon torneo) {
		
		//Este método retorna la cantidad mínima de retos para llegar al resultado
		//de la lista de ganadores del torneo. En caso de que se detecte que el torneo
		//fue saboteado se retornará el valor -1
		
		//variables
		int cantidadDeRetos = 0;
		boolean iterar;
		Pokemon aux;
		
		//Obtiene la cantidad mínima de pasos para llegar al resultado
		
		//Para eso intenta llegar desde el resultado hasta el orden inicial
		//teniendo en cuenta las reglas del juego
		
		//Se hace una copia de la lista de ganadores
		List<Pokemon> ganadores = torneo.getParticipantes();
		
		do {
			
			//Se asume que no se va a iterar mas
			iterar = false;
			
			//Recorre la lista de ganadores desde el último hasta el primero
			for(int i = ganadores.size()-1; i>=0; i--) {
				
				
				//Verifica si el código del pokemon a su izquierda es mayor,
				//de ser así verifica la cantidad de retos y hace el movimiento.
				if( (i-1 >=0) 
					&& (ganadores.get(i).getPokedexcodigo() < ganadores.get(i-1).getPokedexcodigo()) 
					&& (ganadores.get(i-1).getRetosDisponibles() > 0)
					) {
					
					//Se hace el movimiento
					aux = ganadores.get(i);
					ganadores.set(i, ganadores.get(i-1));
					ganadores.set(i-1, aux);
					
					//Se aumenta el contador de retos
					cantidadDeRetos++;
					
					//Se le resta el reto
					ganadores.get(i).disminuirReto();
					
					//Verifica si se debe continuar iterando
					//Verifica si aún tien retos y el codigo del pokemon a la derecha es menor
					if(	(i+1 < ganadores.size()) 
						&& (ganadores.get(i).getPokedexcodigo() > ganadores.get(i+1).getPokedexcodigo()) 
						&& (ganadores.get(i).getRetosDisponibles() > 0)
						) {
						
						iterar = true;
						
					}
					
				}
				
			}
			
			//Verifica si se debe continuar iterando
			/*for(int i=0; i<ganadores.size(); i++) {
				
				//Si el codigo del pokemon es mayor que el de su derecha y aún le quedan retos
				if(	(i+1 < ganadores.size()) 
					&& (ganadores.get(i).getPokedexcodigo() > ganadores.get(i+1).getPokedexcodigo())
					&& (ganadores.get(i).getRetosDisponibles() > 0)
					) {
					
					iterar = true;
					break;
					
				}
				
			}*/
			
		}while(iterar);
		
		//Verifica si la lista quedó ordenada de menor a mayor número de pokedex
		for(int i=0; i<ganadores.size(); i++) {
			
			//Si el codigo es menor al del pokemon de la derecha
			if(	(i+1 < ganadores.size()) 
				&& (ganadores.get(i).getPokedexcodigo() > ganadores.get(i+1).getPokedexcodigo() )
				) {
				
				//Se indica que el torneo fue saboteado por el equipo rocket
				return -1;
				
			}
			
		}
		
		return cantidadDeRetos;
	}

}
