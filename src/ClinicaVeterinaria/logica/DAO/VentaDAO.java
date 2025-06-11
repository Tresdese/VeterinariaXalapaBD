
package ClinicaVeterinaria.logica.DAO;
import ClinicaVeterinaria.logica.dto.VentaDTO;
import ClinicaVeterinaria.logica.dto.DetalleVentaDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VentaDAO extends ConexionBD {
    private final static String SQL_INSERT = "INSERT INTO venta(fecha) VALUES (?)";
    private final static String SQL_UPDATE = "UPDATE venta SET fecha=? WHERE idVenta=?";
    private final static String SQL_DELETE = "DELETE FROM venta WHERE idVenta=?";
    private final static String SQL_SELECT = "SELECT * FROM venta WHERE idVenta=?";
    private final static String SQL_SELECTALL = "SELECT * FROM venta";
    
    public VentaDAO() throws Exception {
        super();
    }
    
    public void agregar(VentaDTO dto) throws Exception {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, dto.getFecha());
            ps.executeUpdate();
            
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    dto.setIdVenta(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new Exception("Error al agregar venta: " + e.getMessage());
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
    
    public void actualizar(VentaDTO dto) throws Exception {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(SQL_UPDATE);
            ps.setString(1, dto.getFecha());
            ps.setInt(2, dto.getIdVenta());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new Exception("Error al actualizar venta: " + e.getMessage());
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
    
    public void eliminar(VentaDTO dto) throws Exception {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(SQL_DELETE);
            ps.setInt(1, dto.getIdVenta());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new Exception("Error al eliminar venta: " + e.getMessage());
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
    
    public VentaDTO buscar(VentaDTO dto) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(SQL_SELECT);
            ps.setInt(1, dto.getIdVenta());
            rs = ps.executeQuery();
            
            if (rs.next()) {
                return new VentaDTO(
                    rs.getInt("idVenta"),
                    rs.getString("fecha")
                );
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new Exception("Error al buscar venta: " + e.getMessage());
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
    
    public List<VentaDTO> listarCompleto() throws Exception {
        List<VentaDTO> lista = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(SQL_SELECTALL);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                lista.add(new VentaDTO(
                    rs.getInt("idVenta"),
                    rs.getString("fecha")
                ));
            }
        } catch (SQLException e) {
            throw new Exception("Error al listar ventas: " + e.getMessage());
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
    
    public boolean registrarVentaCompleta(VentaDTO venta, List<DetalleVentaDTO> detalles) throws Exception {
        PreparedStatement psVenta = null;
        PreparedStatement psDetalle = null;
        ResultSet rs = null;

        try {
            conn.setAutoCommit(false); // Inicia transacción

            // 1. Insertar venta
            psVenta = conn.prepareStatement("INSERT INTO venta(fecha) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
            psVenta.setString(1, venta.getFecha());
            psVenta.executeUpdate();

            rs = psVenta.getGeneratedKeys();
            if (rs.next()) {
                int idVentaGenerado = rs.getInt(1);
                venta.setIdVenta(idVentaGenerado);

                // 2. Insertar todos los detalles
                psDetalle = conn.prepareStatement(
                    "INSERT INTO detalle_venta(cantidad, subTotal, idVenta, idProducto) VALUES (?, ?, ?, ?)"
                );

                for (DetalleVentaDTO det : detalles) {
                    psDetalle.setInt(1, det.getCantidad());
                    psDetalle.setDouble(2, det.getSubTotal());
                    psDetalle.setInt(3, idVentaGenerado);
                    psDetalle.setInt(4, det.getIdProducto());
                    psDetalle.addBatch();
                }

                psDetalle.executeBatch();
            }

            conn.commit(); // Todo salió bien → confirmar cambios
            return true;

        } catch (SQLException e) {
            conn.rollback(); // ?Error → deshacer todo
            throw new Exception("Error en transacción de venta completa: " + e.getMessage());
        } finally {
            if (rs != null) rs.close();
            if (psVenta != null) psVenta.close();
            if (psDetalle != null) psDetalle.close();
            conn.setAutoCommit(true); // Restaurar estado
        }
    }

    public List<Map<String, Object>> obtenerEstadisticasVentas(int mes) throws SQLException {
        CallableStatement cs = null;
        ResultSet rs = null;
        List<Map<String, Object>> resultados = new ArrayList<>();
        try {
            cs = conn.prepareCall("{CALL estadisticasVentasMes(?)}");
            cs.setInt(1, mes);
            boolean hasResult = cs.execute();
            while (hasResult) {
                rs = cs.getResultSet();
                ResultSetMetaData meta = rs.getMetaData();
                int columnCount = meta.getColumnCount();
                while (rs.next()) {
                    Map<String, Object> fila = new HashMap<>();
                    for (int i = 1; i <= columnCount; i++) {
                        fila.put(meta.getColumnName(i), rs.getObject(i));
                    }
                    resultados.add(fila);
                }
                if (rs != null) rs.close();
                hasResult = cs.getMoreResults();
            }
        } finally {
            if (rs != null) rs.close();
            if (cs != null) cs.close();
        }
        return resultados;
    }
    
}