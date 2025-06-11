
package ClinicaVeterinaria.logica.DAO;

import ClinicaVeterinaria.logica.dto.MascotaDTO;

import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class MascotaDAO extends ConexionBD{
    private final static String SQL_INSERT = "INSERT INTO mascota(nombre, especie, raza, color, peso, tamaño, fechaNac) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private final static String SQL_UPDATE = "UPDATE mascota SET nombre=?, especie=?, raza=?, color=?, peso=?, tamaño=?, fechaNac=? WHERE idMascota=?";
    private final static String SQL_DELETE = "DELETE FROM mascota WHERE idMascota=?";
    private final static String SQL_SELECT = "SELECT * FROM mascota WHERE idMascota=?";
    private final static String SQL_SELECTALL = "SELECT * FROM mascota";

    public MascotaDAO() throws Exception {
        super();
    }

     public void agregar(MascotaDTO dto) throws Exception {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, dto.getNombre());
            ps.setString(2, dto.getEspecie());
            ps.setString(3, dto.getRaza());
            ps.setString(4, dto.getColor());
            ps.setString(5, dto.getPeso());
            ps.setString(6, dto.getTamaño());
            ps.setString(7, dto.getFechaNac());
            ps.setInt(8, dto.getIdCliente());
            ps.executeUpdate();
            try(ResultSet generatedKeys = ps.getGeneratedKeys()){
                if(generatedKeys.next()){
                    dto.setIdMascota(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al agregar mascota: " + e.getMessage());
        } finally {
            try {
                if (ps != null) ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void actualizar(MascotaDTO dto) throws Exception {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(SQL_UPDATE);
            ps.setString(1, dto.getNombre());
            ps.setString(2, dto.getEspecie());
            ps.setString(3, dto.getRaza());
            ps.setString(4, dto.getColor());
            ps.setString(5, dto.getPeso());
            ps.setString(6, dto.getTamaño());
            ps.setString(7, dto.getFechaNac());
            ps.setInt(8, dto.getIdCliente());
            ps.setInt(9, dto.getIdMascota());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al actualizar mascota: " + e.getMessage());
        } finally {
            try {
                if (ps != null) ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void eliminar(MascotaDTO dto) throws Exception {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(SQL_DELETE);
            ps.setInt(1, dto.getIdMascota());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al eliminar mascota: " + e.getMessage());
        } finally {
            try {
                if (ps != null) ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public MascotaDTO buscar(MascotaDTO dto) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(SQL_SELECT);
            ps.setInt(1, dto.getIdMascota());
            rs = ps.executeQuery();
            if (rs.next()) {
                dto.setIdMascota(rs.getInt("idMascota"));
                dto.setNombre(rs.getString("nombre"));
                dto.setEspecie(rs.getString("especie"));
                dto.setRaza(rs.getString("raza"));
                dto.setColor(rs.getString("color"));
                dto.setPeso(rs.getString("peso"));
                dto.setTamaño(rs.getString("tamaño"));
                dto.setFechaNac(rs.getString("fechaNac"));
                dto.setIdCliente(rs.getInt("idCliente"));
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al buscar mascota: " + e.getMessage());
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

    public List<MascotaDTO> listarCompleto() throws Exception {
        List<MascotaDTO> lista = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(SQL_SELECTALL);
            rs = ps.executeQuery();
            while (rs.next()) {
                MascotaDTO dto = new MascotaDTO();
                dto.setIdMascota(rs.getInt("idMascota"));
                dto.setNombre(rs.getString("nombre"));
                dto.setEspecie(rs.getString("especie"));
                dto.setRaza(rs.getString("raza"));
                dto.setColor(rs.getString("color"));
                dto.setPeso(rs.getString("peso"));
                dto.setTamaño(rs.getString("tamaño"));
                dto.setFechaNac(rs.getString("fechaNac"));
                dto.setIdCliente(rs.getInt("idCliente"));
                lista.add(dto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al listar mascotas: " + e.getMessage());
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




