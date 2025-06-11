
package ClinicaVeterinaria.logica.DAO;
import ClinicaVeterinaria.logica.dto.ProductoDTO;

import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LEGION
 */
public class ProductoDAO extends ConexionBD {
    private final static String SQL_INSERT = "INSERT INTO producto(nombre, tipo, especie, marca, precio, existencia) VALUES (?, ?, ?, ?, ?, ?)";
    private final static String SQL_UPDATE = "UPDATE producto SET nombre=?, tipo=?, especie=?, marca=?, precio=?, existencia=?, fechaNac=? WHERE idProducto=?";
    private final static String SQL_DELETE = "DELETE FROM producto WHERE idMascota=?";
    private final static String SQL_SELECT = "SELECT * FROM producto WHERE idMascota=?";
    private final static String SQL_SELECTALL = "SELECT * FROM producto";
    
    public ProductoDAO() throws Exception{
        super();
    }
    
     public void agregar(ProductoDTO dto) throws Exception {
         PreparedStatement ps = null;
         try{
            ps = conn.prepareStatement(SQL_INSERT);
            ps.setString(1, dto.getNombre());
            ps.setString(2, dto.getTipo());
            ps.setString(3, dto.getEspecie());
            ps.setString(4, dto.getMarca());
            ps.setDouble(5, dto.getPrecio());
            ps.setInt(6, dto.getExistencia());
            ps.executeUpdate();
            
            try(ResultSet generatedKeys = ps.getGeneratedKeys()){
                if(generatedKeys.next()){
                    dto.setIdProducto(generatedKeys.getInt(1));
                }
            }
         }catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al agregar producto: " + e.getMessage());
        } finally {
            try {
                if (ps != null) ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
     }
     
     public void actualizar(ProductoDTO dto) throws Exception {
        PreparedStatement ps = null;
        try {
            ps.setString(1, dto.getNombre());
            ps.setString(2, dto.getTipo());
            ps.setString(3, dto.getEspecie());
            ps.setString(4, dto.getMarca());
            ps.setDouble(5, dto.getPrecio());
            ps.setInt(6, dto.getExistencia());
            ps.setInt(7, dto.getIdProducto());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al actualizar producto: " + e.getMessage());
        } finally {
            try {
                if (ps != null) ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void eliminar(ProductoDTO dto) throws Exception {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(SQL_DELETE);
            ps.setInt(1, dto.getIdProducto());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al eliminar producto: " + e.getMessage());
        } finally {
            try {
                if (ps != null) ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public ProductoDTO buscar(ProductoDTO dto) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(SQL_SELECT);
            ps.setInt(1, dto.getIdProducto());
            rs = ps.executeQuery();
            if (rs.next()) {
                dto.setIdProducto(rs.getInt("idProducto"));
                dto.setNombre(rs.getString("nombre"));
                dto.setTipo(rs.getString("tipo"));
                dto.setEspecie(rs.getString("especie"));
                dto.setMarca(rs.getString("marca"));
                dto.setPrecio(rs.getFloat("precio"));
                dto.setExistencia(rs.getInt("existencia"));
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al buscar producto: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return dto;
    }

    public List<ProductoDTO> listarCompleto() throws Exception {
        List<ProductoDTO> lista = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(SQL_SELECTALL);
            rs = ps.executeQuery();
            while (rs.next()) {
                ProductoDTO dto = new ProductoDTO(
                rs.getInt("idProducto"),
                rs.getString("nombre"),
                rs.getString("tipo"),
                rs.getString("especie"),
                rs.getString("marca"),
                rs.getFloat("precio"),
                rs.getInt("existencia"));
                lista.add(dto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al listar productos: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return lista;
    }

    public int contarProductosPorNombre(String nombreProducto) throws SQLException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement("SELECT contarProductosPorNombre(?)");
            stmt.setString(1, nombreProducto);
            rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
            return 0;
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
        }
    }
    
}
