package baseDeDatos;

import java.sql.*;

public class DatosRiego {
	// Método para guardar un evento de riego en el historial
    public void guardarRiego(int idAgricultor, String cultivo, double temp, double hum, String estrategia) {
        Connection conexion = null;
        PreparedStatement ps = null;
        
        String sql = "INSERT INTO historial_riego " +
                     "(id_agricultor, nombre_cultivo, temp_detectada, humedad_detectada, estrategia_aplicada) " +
                     "VALUES (?, ?, ?, ?, ?)";

        try {
            conexion = ConexionBD.conectar();
            ps = conexion.prepareStatement(sql);
            
            ps.setInt(1, idAgricultor);
            ps.setString(2, cultivo);
            ps.setDouble(3, temp);
            ps.setDouble(4, hum);
            ps.setString(5, estrategia);
            
            ps.executeUpdate();
            System.out.println(">> Riego guardado en historial para: " + cultivo);

        } catch (SQLException e) {
            System.err.println("Error al guardar historial: " + e.getMessage());
        } finally {
            try {
                if (ps != null) ps.close();
                if (conexion != null) conexion.close();
            } catch (SQLException e) {}
        }
    }
}
