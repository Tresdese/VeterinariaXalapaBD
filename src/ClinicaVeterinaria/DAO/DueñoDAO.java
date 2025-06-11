
package ClinicaVeterinaria.DAO;

import ClinicaVeterinaria.dto.DueñoDTO;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class DueñoDAO extends ConexionBD{
    private final static String SQL_INSERT = "INSERT INTO dueño(apellido_M, apellido_P, nombre, numDirCliente, telefono) VALUES (?, ?, ?, ?, ?)";
    private final static String SQL_UPDATE = "UPDATE dueño SET apellido_M=?, apellido_P=?, nombre=?, numDirCliente=?, telefono=? WHERE idCliente=?";
    private final static String SQL_DELETE = "DELETE FROM dueño WHERE idCliente=?";
    private final static String SQL_SELECT = "SELECT * FROM dueño WHERE idCliente=?";
    private final static String SQL_SELECTALL = "SELECT * FROM dueño";

     public DueñoDAO() throws Exception {
        super();
    }

    public void agregar(DueñoDTO dto) throws Exception {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, dto.getApellido_M());
            ps.setString(2, dto.getApellido_P());
            ps.setString(3, dto.getNombre());
            ps.setString(4, dto.getNumDirCliente());
            ps.setString(5, dto.getTelefono());
            ps.executeUpdate();
            try(ResultSet generatedKeys = ps.getGeneratedKeys()){
                if(generatedKeys.next()){
                    dto.setIdCliente(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al agregar dueño: " + e.getMessage());
        } finally {
            try {
                if (ps != null) ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void actualizar(DueñoDTO dto) throws Exception {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(SQL_UPDATE);
            ps.setString(1, dto.getApellido_M());
            ps.setString(2, dto.getApellido_P());
            ps.setString(3, dto.getNombre());
            ps.setString(4, dto.getNumDirCliente());
            ps.setString(5, dto.getTelefono());
            ps.setInt(6, dto.getIdCliente());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al actualizar dueño: " + e.getMessage());
        } finally {
            try {
                if (ps != null) ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void eliminar(DueñoDTO dto) throws Exception {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(SQL_DELETE);
            ps.setInt(1, dto.getIdCliente());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al eliminar dueño: " + e.getMessage());
        } finally {
            try {
                if (ps != null) ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public DueñoDTO buscar(DueñoDTO dto) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(SQL_SELECT);
            ps.setInt(1, dto.getIdCliente());
            rs = ps.executeQuery();
            if (rs.next()) {
                dto.setIdCliente(rs.getInt("idCliente"));
                dto.setApellido_M(rs.getString("apellido_M"));
                dto.setApellido_P(rs.getString("apellido_P"));
                dto.setNombre(rs.getString("nombre"));
                dto.setNumDirCliente(rs.getString("numDirCliente"));
                dto.setTelefono(rs.getString("telefono"));
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al buscar dueño: " + e.getMessage());
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

    public List<DueñoDTO> listarCompleto() throws Exception {
        List<DueñoDTO> lista = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(SQL_SELECTALL);
            rs = ps.executeQuery();
            while (rs.next()) {
                DueñoDTO dto = new DueñoDTO();
                dto.setIdCliente(rs.getInt("idCliente"));
                dto.setApellido_M(rs.getString("apellido_M"));
                dto.setApellido_P(rs.getString("apellido_P"));
                dto.setNombre(rs.getString("nombre"));
                dto.setNumDirCliente(rs.getString("numDirCliente"));
                dto.setTelefono(rs.getString("telefono"));
                lista.add(dto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al listar dueños: " + e.getMessage());
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
}

