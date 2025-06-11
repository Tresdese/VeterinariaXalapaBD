package ClinicaVeterinaria.logica.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductosNoVendidosDAO {
    private ConexionBD conexionBD;

    public ProductosNoVendidosDAO() throws Exception {
        this.conexionBD = new ConexionBD();
    }

    public List<String[]> obtenerProductosNoVendidos() throws Exception {
        List<String[]> productos = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT * FROM vista_productos_no_vendidos";
            pstmt = conexionBD.conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                String[] producto = new String[4];
                producto[0] = rs.getString("nombre_cliente");
                producto[1] = rs.getString("producto_1") != null ? rs.getString("producto_1") : "N/A";
                producto[2] = rs.getString("producto_2") != null ? rs.getString("producto_2") : "N/A";
                producto[3] = rs.getString("producto_3") != null ? rs.getString("producto_3") : "N/A";
                productos.add(producto);
            }
        } catch (SQLException e) {
            throw new Exception("Error al obtener productos no vendidos: " + e.getMessage());
        } finally {
            conexionBD.cerrar(pstmt);
            if (rs != null) rs.close();
        }

        return productos;
    }

    public void mostrarProductosNoVendidos() throws Exception {
        List<String[]> productos = obtenerProductosNoVendidos();

        System.out.println("\n--- PRODUCTOS NO VENDIDOS A CLIENTES ---");
        System.out.printf("%-40s %-20s %-20s %-20s%n",
                "Cliente", "Producto 1", "Producto 2", "Producto 3");

        for (String[] producto : productos) {
            System.out.printf("%-40s %-20s %-20s %-20s%n",
                    producto[0], producto[1], producto[2], producto[3]);
        }
    }
}