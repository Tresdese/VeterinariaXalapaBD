
package ClinicaVeterinaria.logica.DAO;
import ClinicaVeterinaria.logica.dto.DetalleVentaDTO;

import java.sql.*;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author LEGION
 */
public class DetalleVentaDAO extends ConexionBD{
    private final static String SQL_INSERT = "INSERT INTO detalle_venta(cantidad, subTotal, idVenta, idProducto) VALUES (?, ?, ?, ?)";
    private final static String SQL_UPDATE = "UPDATE detalle_venta SET cantidad=?, subTotal=?, idVenta=?, idProducto=? WHERE idDetalle=?";
    private final static String SQL_DELETE = "DELETE FROM detalle_venta WHERE idDetalle=?";
    private final static String SQL_SELECT = "SELECT * FROM detalle_venta WHERE idDetalle=?";
    private final static String SQL_SELECTALL = "SELECT * FROM detalle_venta";
    
     public DetalleVentaDAO() throws Exception {
        super();
    }
     
    public void agregar(DetalleVentaDTO dto) throws Exception {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, dto.getCantidad());
            ps.setDouble(2, dto.getSubTotal());
            ps.setInt(3, dto.getIdVenta());
            ps.setInt(4, dto.getIdProducto());
            ps.executeUpdate();
            
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    dto.setIdDetalle(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new Exception("Error al agregar detalle de venta: " + e.getMessage());
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    System.err.println("Error al cerrar PreparedStatement: " + e.getMessage());
                }
            }
        }
    }
    
    public void actualizar(DetalleVentaDTO dto) throws Exception {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(SQL_UPDATE);
            ps.setInt(1, dto.getCantidad());
            ps.setDouble(2, dto.getSubTotal());
            ps.setInt(3, dto.getIdVenta());
            ps.setInt(4, dto.getIdProducto());
            ps.setInt(5, dto.getIdDetalle());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new Exception("Error al actualizar detalle de venta: " + e.getMessage());
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    System.err.println("Error al cerrar PreparedStatement: " + e.getMessage());
                }
            }
        }
    }
    
    public void eliminar(DetalleVentaDTO dto) throws Exception {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(SQL_DELETE);
            ps.setInt(1, dto.getIdDetalle());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new Exception("Error al eliminar detalle de venta: " + e.getMessage());
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    System.err.println("Error al cerrar PreparedStatement: " + e.getMessage());
                }
            }
        }
    }
    
    public DetalleVentaDTO buscar(DetalleVentaDTO dto) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(SQL_SELECT);
            ps.setInt(1, dto.getIdDetalle());
            rs = ps.executeQuery();
            
            if (rs.next()) {
                return new DetalleVentaDTO(
                    rs.getInt("idDetalle"),
                    rs.getInt("cantidad"),
                    rs.getDouble("subTotal"),
                    rs.getInt("idVenta"),
                    rs.getInt("idProducto")
                );
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new Exception("Error al buscar detalle de venta: " + e.getMessage());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    System.err.println("Error al cerrar ResultSet: " + e.getMessage());
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    System.err.println("Error al cerrar PreparedStatement: " + e.getMessage());
                }
            }
        }
    }
    
    public List<DetalleVentaDTO> listarCompleto() throws Exception {
        List<DetalleVentaDTO> lista = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(SQL_SELECTALL);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                lista.add(new DetalleVentaDTO(
                    rs.getInt("idDetalle"),
                    rs.getInt("cantidad"),
                    rs.getDouble("subTotal"),
                    rs.getInt("idVenta"),
                    rs.getInt("idProducto")
                ));
            }
        } catch (SQLException e) {
            throw new Exception("Error al listar detalles de venta: " + e.getMessage());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    System.err.println("Error al cerrar ResultSet: " + e.getMessage());
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    System.err.println("Error al cerrar PreparedStatement: " + e.getMessage());
                }
            }
        }
        return lista;
    }
}
