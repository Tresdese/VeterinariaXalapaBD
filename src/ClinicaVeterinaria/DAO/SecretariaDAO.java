package ClinicaVeterinaria.DAO;

import ClinicaVeterinaria.dto.SecretariaDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SecretariaDAO extends ConexionBD {
    private final static String SQL_INSERT = "INSERT INTO secretaria(nombre, apellidoP, apellidoM, numeroINE, telefono, dirSecretaria, nombreUsuario, contraseña) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private final static String SQL_UPDATE = "UPDATE secretaria SET nombre=?, apellidoP=?, apellidoM=?, numeroINE=?, telefono=?, dirSecretaria=?, nombreUsuario=?, contraseña=? WHERE idSecretaria=?";
    private final static String SQL_DELETE = "DELETE FROM secretaria WHERE idSecretaria=?";
    private final static String SQL_SELECT = "SELECT * FROM secretaria WHERE idSecretaria=?";
    private final static String SQL_SELECTALL = "SELECT * FROM secretaria";
    private final static String SQL_LOGIN = "SELECT * FROM secretaria WHERE nombreUsuario=? AND contraseña=?";

    public SecretariaDAO() throws Exception {
        super();
    }

    public void agregar(SecretariaDTO dto) throws Exception {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, dto.getNombre());
            ps.setString(2, dto.getApellidoP());
            ps.setString(3, dto.getApellidoM());
            ps.setString(4, dto.getNumeroINE());
            ps.setString(5, dto.getTelefono());
            ps.setString(6, dto.getDirSecretaria());
            ps.setString(7, dto.getNombreUsuario());
            ps.setString(8, dto.getContraseña());
            ps.executeUpdate();

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    dto.setIdSecretaria(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new Exception("Error al agregar secretaria: " + e.getMessage());
        } finally {
            if (ps != null) ps.close();
        }
    }

    public void actualizar(SecretariaDTO dto) throws Exception {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(SQL_UPDATE);
            ps.setString(1, dto.getNombre());
            ps.setString(2, dto.getApellidoP());
            ps.setString(3, dto.getApellidoM());
            ps.setString(4, dto.getNumeroINE());
            ps.setString(5, dto.getTelefono());
            ps.setString(6, dto.getDirSecretaria());
            ps.setString(7, dto.getNombreUsuario());
            ps.setString(8, dto.getContraseña());
            ps.setInt(9, dto.getIdSecretaria());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new Exception("Error al actualizar secretaria: " + e.getMessage());
        } finally {
            if (ps != null) ps.close();
        }
    }

    public void eliminar(SecretariaDTO dto) throws Exception {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(SQL_DELETE);
            ps.setInt(1, dto.getIdSecretaria());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new Exception("Error al eliminar secretaria: " + e.getMessage());
        } finally {
            if (ps != null) ps.close();
        }
    }

    public SecretariaDTO buscar(SecretariaDTO dto) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(SQL_SELECT);
            ps.setInt(1, dto.getIdSecretaria());
            rs = ps.executeQuery();

            if (rs.next()) {
                return new SecretariaDTO(
                        rs.getInt("idSecretaria"),
                        rs.getString("nombre"),
                        rs.getString("apellidoP"),
                        rs.getString("apellidoM"),
                        rs.getString("numeroINE"),
                        rs.getString("telefono"),
                        rs.getString("dirSecretaria"),
                        rs.getString("nombreUsuario"),
                        rs.getString("contraseña")
                );
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new Exception("Error al buscar secretaria: " + e.getMessage());
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
        }
    }

    public List<SecretariaDTO> listarCompleto() throws Exception {
        List<SecretariaDTO> lista = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(SQL_SELECTALL);
            rs = ps.executeQuery();

            while (rs.next()) {
                lista.add(new SecretariaDTO(
                        rs.getInt("idSecretaria"),
                        rs.getString("nombre"),
                        rs.getString("apellidoP"),
                        rs.getString("apellidoM"),
                        rs.getString("numeroINE"),
                        rs.getString("telefono"),
                        rs.getString("dirSecretaria"),
                        rs.getString("nombreUsuario"),
                        rs.getString("contraseña")
                ));
            }
        } catch (SQLException e) {
            throw new Exception("Error al listar secretarias: " + e.getMessage());
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
        }
        return lista;
    }

    public SecretariaDTO autenticar(String nombreUsuario, String contraseña) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(SQL_LOGIN);
            ps.setString(1, nombreUsuario);
            ps.setString(2, contraseña);
            rs = ps.executeQuery();

            if (rs.next()) {
                return new SecretariaDTO(
                        rs.getInt("idSecretaria"),
                        rs.getString("nombre"),
                        rs.getString("apellidoP"),
                        rs.getString("apellidoM"),
                        rs.getString("numeroINE"),
                        rs.getString("telefono"),
                        rs.getString("dirSecretaria"),
                        rs.getString("nombreUsuario"),
                        rs.getString("contraseña")
                );
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new Exception("Error al autenticar secretaria: " + e.getMessage());
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
        }
    }
}