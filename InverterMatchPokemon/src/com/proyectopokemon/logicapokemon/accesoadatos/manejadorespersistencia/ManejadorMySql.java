package com.proyectopokemon.logicapokemon.accesoadatos.manejadorespersistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

import com.proyectopokemon.logicapokemon.accesoadatos.modelosmanejadores.RespuestaInfoPokemon;
import com.proyectopokemon.logicapokemon.clasespokemon.ComparadorPokemonNombre;
import com.proyectopokemon.logicapokemon.clasespokemon.Pokemon;
import com.proyectopokemon.logicapokemon.clasespokemon.TorneoPokemon;

/**
 * @author EstebanTrivino
 *
 */
public class ManejadorMySql implements IManejadorDatos {

	// Variable de conexion
	private Connection con;

	// Constantes privadas de conexi�n por cuesti�n de tiempo se dejaron quemadas
	//y no se consultaron desde un archivo encriptado para no tener que recompilar
	//cuando se cambien las credenciales o se despliegue en otro servidor
	private final String driver = "com.mysql.cj.jdbc.Driver";
	private final String user = "root";
	private final String pass = "Clave1root";
	private final String url = "jdbc:mysql://localhost:3306/pokeapp?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

	// M�todos propios
	private void conectarBD() {

		con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, pass);
			// Verifica la conexion
			if (con != null) {
				System.out.println("Conexi�n establecida!");
			}
		} catch (ClassNotFoundException e) {
			System.out.println("No se pudo establecer la conexi�n!!");
		} catch (SQLException e) {
			System.out.println("No se pudo establecer la conexi�n!");
			e.printStackTrace();
		}

	}

	@Override
	public RespuestaInfoPokemon obtenerPokeInfoPorNombre(RespuestaInfoPokemon pokemon) {
		
		return null;
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
			
			//Se cierra el cursor de la conexi�n
			resultado.close();
			//Se cierra la conexi�n
			con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public Hashtable<String, Pokemon> obtenerTodosLosPokemones() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void guardarTorneo(TorneoPokemon torneo) {
		

		//Realiza la insersi�n en la base de datos
		try {

			//Se conecta a la base de datos
			conectarBD();
			
			con.setAutoCommit(false);
			
			//Crea la onsulta
			String consulta = "insert into torneos (descrpcion) values (?)";
			
			//Prepara la sentencia
			PreparedStatement preparedStmt = con.prepareStatement(consulta);
			
			//A�ade los valores a la sentencia
		     preparedStmt.setString (1, torneo.getNombre());
		     
		     //Se ejecuta el insert
		     preparedStmt.execute();
		     
		     //Asienta la transacci�n
		     con.commit();
		     
		     //Se cierra la conexi�n
		     con.close();
			
		}catch (Exception e) {
			try {
				//Se deshacen los cambios
				con.rollback();
				//Se cierra la conexi�n
			    con.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}

}
