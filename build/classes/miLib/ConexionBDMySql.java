package miLib;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBDMySql {
	private static final String URL = "jdbc:mysql://localhost:3306/";
	private static final String USUARIO = "root";
	private static final String CONTRASENA = "mysql";
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (Exception e) {
			System.out.println("Error de mysql-connector: "+e.getMessage());
		}
	}
	//Conectarse a la BD
	public static Connection obtenerConexion(String esquemaBD) {
		try {
			return DriverManager.getConnection(URL+esquemaBD, USUARIO, CONTRASENA);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Error al conectar a la base de datos.");
		}
	}
	
	//Prueba de Conexión
	public static void main(String[] args) {
		Connection cnx= ConexionBDMySql.obtenerConexion("db_eventos");
		if(cnx!=null) {
			System.out.println("Conexión exitosa...");
		}
	}
}