
package ClinicaVeterinaria.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConexionBD {
    protected Connection conn;
    protected void cerrar(PreparedStatement rst)throws Exception{
        if (rst != null) {  // Solo intenta cerrar si no es null
        rst.close();
        }
    }
    
    public ConexionBD() throws Exception {
        String driver = "com.mysql.cj.jdbc.Driver";
        String user = "vet_user";
        String pass = "v3Ter1n4#$";
        String basedatos = "veterinariaxalapa";
        String server = "jdbc:mysql://localhost:3306/" + basedatos;
        
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(server, user, pass);
        } catch (SQLException e) {
            throw new Exception("Error al conectar a la base de datos: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new Exception("Error al cargar el driver JDBC: " + e.getMessage());
        }
    }       
    
    public boolean probarConexion() {
        try {
            return conn != null && !conn.isClosed() && conn.isValid(2);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}


