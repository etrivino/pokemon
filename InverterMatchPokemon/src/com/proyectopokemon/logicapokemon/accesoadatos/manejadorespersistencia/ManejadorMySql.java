package com.proyectopokemon.logicapokemon.accesoadatos.manejadorespersistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.List;

import com.proyectopokemon.logicapokemon.clasespokemon.ComparadorPokemonNombre;
import com.proyectopokemon.logicapokemon.clasespokemon.Pokemon;

/**
 * @author EstebanTriviño
 *
 */
public class ManejadorMySql implements IManejadorDatos {

	// Variable de conexion
	private Connection con;

	// Constantes privadas de conexión por cuestión de tiempo se dejaron quemadas
	//y no se consultaron desde un archivo encriptado para no tener que recompilar
	//cuando se cambien las credenciales o se despliegue en otro servidor
	private final String driver = "com.mysql.cj.jdbc.Driver";
	private final String user = "root";
	private final String pass = "Clave1root";
	private final String url = "jdbc:mysql://localhost:3306/pokeapp?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

	// Métodos propios
	private void conectarBD() {

		con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, pass);
			// Verifica la conexion
			if (con != null) {
				System.out.println("Conexión establecida!");
			}
		} catch (ClassNotFoundException e) {
			System.out.println("No se pudo establecer la conexión!!");
		} catch (SQLException e) {
			System.out.println("No se pudo establecer la conexión!");
			e.printStackTrace();
		}

	}

	@Override
	public void obtenerPokeInfoPorNombre(Pokemon pokemon) {
		
		//Se conecta a la base de datos
		conectarBD();

	}

	@Override
	public void obtenerPokeInfo(List<Pokemon> pokemones) {
		
		//Arma la consulta
		String consulta = "SELECT nombre, pokedex FROM pokemones WHERE nombre in (";
		
		int i;
		for(i=0; i<pokemones.size()-1; i++) {
			consulta += "'"+pokemones.get(i).getNombre() + "',";
		}
		
		consulta += "'"+pokemones.get(i).getNombre() + "')";
		
		System.out.println(consulta);
		
		//Limpia la lista
		pokemones.clear();
		
		//Realiza la consulta en la base de datos
		try {

			//Se conecta a la base de datos
			conectarBD();
			
			//Crea la sentencia
			Statement sentencia = con.createStatement();
			
			//Ejecuta la consulta
			ResultSet resultado = sentencia.executeQuery(consulta);
			
			//Vacia el resultado en la lista
			while(resultado.next()) {
				
				pokemones.add(new Pokemon(resultado.getInt("pokedex"), resultado.getString("nombre")));
				
			}
			
			//Se cierra el cursor de la conexión
			resultado.close();
			//Se cierra la conexión
			con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
