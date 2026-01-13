package baseDeDatos;

import java.sql.*;
import javax.swing.JOptionPane;

public class DatosAgricultor {
	public boolean registrarAgricultor(String dni, String nombre, String apellido, String region) {
		Connection conexion = null;
        PreparedStatement psVerificar = null;
        PreparedStatement psInsertar = null;
        ResultSet rs = null;

        try {
            conexion = ConexionBD.conectar();
            
            // Preguntar si el DNI ya existe
            String sqlVerificar = "SELECT id_agricultor FROM agricultor WHERE dni = ?";
            psVerificar = conexion.prepareStatement(sqlVerificar);
            psVerificar.setString(1, dni);
            rs = psVerificar.executeQuery();
            
            if (rs.next()) {
                // Si entra aquí, significa que ya existe el DNI
                JOptionPane.showMessageDialog(null, "El DNI " + dni + " ya está registrado.\nUse el botón 'Iniciar Sesión'.");
                return false; // Retornamos false y NO hacemos el INSERT
            }
            
            // Si no existe, recién hacemos el INSERT
            String sqlInsertar = "INSERT INTO agricultor (dni, nombre, apellido, region) VALUES (?, ?, ?, ?)";
            psInsertar = conexion.prepareStatement(sqlInsertar);
            psInsertar.setString(1, dni);
            psInsertar.setString(2, nombre);
            psInsertar.setString(3, apellido);
            psInsertar.setString(4, region);
            
            psInsertar.executeUpdate();
            return true; // Registro exitoso

        } catch (SQLException e) {
            System.err.println("Error al registrar: " + e.getMessage());
            return false;
        } finally {
            try { 
                if (rs != null) rs.close();
                if (psVerificar != null) psVerificar.close();
                if (psInsertar != null) psInsertar.close();
                if (conexion != null) conexion.close(); 
            } catch (SQLException e) {}
        }
	}
    public String[] loginAgricultor(String dni) {
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String sql = "SELECT id_agricultor, nombre, region FROM agricultor WHERE dni = ?";
        
        // Inicializamos el arreglo en null
        String[] datosEncontrados = null;

        try {
            conexion = ConexionBD.conectar(); // Usamos clase ConexionBD
            ps = conexion.prepareStatement(sql);
            ps.setString(1, dni);
            rs = ps.executeQuery();

            if (rs.next()) {
                String id = String.valueOf(rs.getInt("id_agricultor"));
                String nombre = rs.getString("nombre");
                String region = rs.getString("region"); 
                
                datosEncontrados = new String[] { id, nombre, region };
                
                JOptionPane.showMessageDialog(null, "¡Bienvenido, " + nombre + "!");
            }
        } catch (SQLException e) {
            System.err.println("Error al loguear: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conexion != null) conexion.close();
            } catch (SQLException e) {
                System.out.println("Error cerrando conexión");
            }
        }
        return datosEncontrados;
    }
}
