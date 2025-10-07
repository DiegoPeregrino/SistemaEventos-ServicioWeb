package miLib;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.InputStream;

public class ConexionBDMySql {
	private static String URL_BASE = "jdbc:mysql://localhost:3306/";
	private static String USUARIO = "root";
	private static String CONTRASENA = "mysql";
	private static String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
	private static String DB_PARAMS = ""; // e.g. useSSL=false&serverTimezone=UTC
	private static String DEFAULT_SCHEMA = "";

	static {
		try {
			Class.forName(DB_DRIVER);
		} catch (Exception e) {
			System.out.println("Error de mysql-connector: " + e.getMessage());
		}

		// Intentar cargar configuración desde application.properties en el classpath
		try (InputStream in = ConexionBDMySql.class.getClassLoader().getResourceAsStream("application.properties")) {
			if (in != null) {
				Properties p = new Properties();
				p.load(in);
				String url = p.getProperty("db.url");
				if (url != null && !url.trim().isEmpty()) {
					URL_BASE = url;
				}
				String driver = p.getProperty("db.driver");
				if (driver != null && !driver.trim().isEmpty()) {
					DB_DRIVER = driver;
				}
				String params = p.getProperty("db.params");
				if (params != null) {
					DB_PARAMS = params.trim();
				}
				String defaultSchema = p.getProperty("db.defaultSchema");
				if (defaultSchema != null && !defaultSchema.trim().isEmpty()) {
					DEFAULT_SCHEMA = defaultSchema.trim();
				}
				String user = p.getProperty("db.user");
				if (user != null && !user.trim().isEmpty()) {
					USUARIO = user;
				}
				String pass = p.getProperty("db.password");
				if (pass != null) {
					CONTRASENA = pass;
				}
			}
		} catch (Exception e) {
			System.out.println("No se pudo leer application.properties: " + e.getMessage());
		}
	}

	// Conectarse a la BD
	public static Connection obtenerConexion(String esquemaBD) {
		try {
			String fullUrl = URL_BASE + esquemaBD;
			if (DB_PARAMS != null && !DB_PARAMS.isEmpty()) {
				if (fullUrl.contains("?")) fullUrl += "&" + DB_PARAMS;
				else fullUrl += "?" + DB_PARAMS;
			}
			return DriverManager.getConnection(fullUrl, USUARIO, CONTRASENA);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Error al conectar a la base de datos.");
		}
	}

	/**
	 * Obtiene una conexión usando el esquema por defecto declarado en properties.
	 * Si no existe schema por defecto, lanza RuntimeException.
	 */
	public static Connection obtenerConexion() {
		if (DEFAULT_SCHEMA == null || DEFAULT_SCHEMA.isEmpty()) {
			throw new RuntimeException("No hay esquema por defecto configurado (db.defaultSchema)");
		}
		return obtenerConexion(DEFAULT_SCHEMA);
	}

	// Prueba de Conexión
	public static void main(String[] args) {
		Connection cnx = ConexionBDMySql.obtenerConexion("casobd");
		if (cnx != null) {
			System.out.println("Conexión exitosa...");
		}
	}
}
