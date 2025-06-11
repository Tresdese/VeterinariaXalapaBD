

package ClinicaVeterinaria.logica.DAO;
/**
 *
 * @author LEGION
 */
import ClinicaVeterinaria.logica.dto.VeterinarioDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VeterinarioDAO extends ConexionBD {
    // Consultas SQL
    private final static String SQL_INSERT = "INSERT INTO veterinario(nombre, apellidoP, apellidoM, cedulaProf, telefono, dirVet, nombreUsuario, contraseña) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private final static String SQL_UPDATE = "UPDATE veterinario SET nombre=?, apellidoP=?, apellidoM=?, cedulaProf=?, telefono=?, dirVet=?, nombreUsuario=?, contraseña=? WHERE idVeterinario=?";
    private final static String SQL_DELETE = "DELETE FROM veterinario WHERE idVeterinario=?";
    private final static String SQL_SELECT = "SELECT * FROM veterinario WHERE idVeterinario=?";
    private final static String SQL_SELECTALL = "SELECT * FROM veterinario";
    private final static String SQL_LOGIN = "SELECT * FROM veterinario WHERE nombreUsuario=? AND contraseña=?";

    public VeterinarioDAO() throws Exception {
        super();
    }
    
    public void agregar(VeterinarioDTO dto) throws Exception {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, dto.getNombre());
            ps.setString(2, dto.getApellidoP());
            ps.setString(3, dto.getApellidoM());
            ps.setString(4, dto.getCedulaProf());
            ps.setString(5, dto.getTelefono());
            ps.setString(6, dto.getDirVet());
            ps.setString(7, dto.getNombreUsuario());
            ps.setString(8, dto.getContraseña());
            ps.executeUpdate();
            
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    dto.setIdVeterinario(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new Exception("Error al agregar veterinario: " + e.getMessage());
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
    
    public void actualizar(VeterinarioDTO dto) throws Exception {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(SQL_UPDATE);
            ps.setString(1, dto.getNombre());
            ps.setString(2, dto.getApellidoP());
            ps.setString(3, dto.getApellidoM());
            ps.setString(4, dto.getCedulaProf());
            ps.setString(5, dto.getTelefono());
            ps.setString(6, dto.getDirVet());
            ps.setString(7, dto.getNombreUsuario());
            ps.setString(8, dto.getContraseña());
            ps.setInt(9, dto.getIdVeterinario());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new Exception("Error al actualizar veterinario: " + e.getMessage());
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
    
    public void eliminar(VeterinarioDTO dto) throws Exception {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(SQL_DELETE);
            ps.setInt(1, dto.getIdVeterinario());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new Exception("Error al eliminar veterinario: " + e.getMessage());
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
    
    public VeterinarioDTO buscar(VeterinarioDTO dto) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(SQL_SELECT);
            ps.setInt(1, dto.getIdVeterinario());
            rs = ps.executeQuery();
            
            if (rs.next()) {
                return new VeterinarioDTO(
                    rs.getInt("idVeterinario"),
                    rs.getString("nombre"),
                    rs.getString("apellidoP"),
                    rs.getString("apellidoM"),
                    rs.getString("cedulaProf"),
                    rs.getString("telefono"),
                    rs.getString("dirVet"),
                    rs.getString("nombreUsuario"),
                    rs.getString("contraseña")
                );
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new Exception("Error al buscar veterinario: " + e.getMessage());
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
    
    public List<VeterinarioDTO> listarCompleto() throws Exception {
        List<VeterinarioDTO> lista = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(SQL_SELECTALL);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                lista.add(new VeterinarioDTO(
                    rs.getInt("idVeterinario"),
                    rs.getString("nombre"),
                    rs.getString("apellidoP"),
                    rs.getString("apellidoM"),
                    rs.getString("cedulaProf"),
                    rs.getString("telefono"),
                    rs.getString("dirVet"),
                    rs.getString("nombreUsuario"),
                    rs.getString("contraseña")
                ));
            }
        } catch (SQLException e) {
            throw new Exception("Error al listar veterinarios: " + e.getMessage());
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
    
    // Método adicional para autenticación
    public VeterinarioDTO autenticar(String nombreUsuario, String contraseña) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(SQL_LOGIN);
            ps.setString(1, nombreUsuario);
            ps.setString(2, contraseña);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                return new VeterinarioDTO(
                    rs.getInt("idVeterinario"),
                    rs.getString("nombre"),
                    rs.getString("apellidoP"),
                    rs.getString("apellidoM"),
                    rs.getString("cedulaProf"),
                    rs.getString("telefono"),
                    rs.getString("dirVet"),
                    rs.getString("nombreUsuario"),
                    rs.getString("contraseña")
                );
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new Exception("Error al autenticar veterinario: " + e.getMessage());
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

    public int consultasRealizadasEnMes(int vetId, int mes) throws SQLException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement("SELECT consultasMes(?, ?)");
            stmt.setInt(1, vetId);
            stmt.setInt(2, mes);
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

    public boolean verificarDisponibilidad(int idVet, Date fecha, Timestamp hora) throws SQLException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement("SELECT verificarDisponibilidadVeterinario(?, ?, ?)");
            stmt.setInt(1, idVet);
            stmt.setDate(2, fecha);
            stmt.setTimestamp(3, hora);
            rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getBoolean(1);
            }
            return false;
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
        }
    }

    public List<String> obtenerVeterinariosDisponibles(Date fecha, Timestamp hora) throws SQLException {
        CallableStatement cs = null;
        ResultSet rs = null;
        List<String> veterinarios = new ArrayList<>();
        try {
            cs = conn.prepareCall("{CALL obtenerVeterinariosDisponibles(?, ?)}");
            cs.setDate(1, fecha);
            cs.setTimestamp(2, hora);
            rs = cs.executeQuery();
            while (rs.next()) {
                veterinarios.add(rs.getString("nombreCompleto"));
            }
            return veterinarios;
        } finally {
            if (rs != null) rs.close();
            if (cs != null) cs.close();
        }
    }
}
