package baseDeDatos;

import java.sql.*;

public class ConexionBD {
	private static final String url="jdbc:mysql://localhost/sistema_riego_bd";
	private static final String user="root";
	private static final String password="";
	private static final String driver="com.mysql.cj.jdbc.Driver";
	
	public static Connection conectar() {
		Connection conexion=null;
		try {
			Class.forName(driver);
			conexion=DriverManager.getConnection(url,user,password);
			System.out.println("<< ¡Conexión exitosa a la base de datos! >>");
		} catch(ClassNotFoundException e) {
			System.out.println("¡Error! Falta el Driver JDBC de MySQL en el proyecto.");
		} catch(SQLException e) {
			System.out.println("¡Error SQL! No se pudo conectar. Verifica usuario/clave.");
		}
		return conexion;
	}
}
