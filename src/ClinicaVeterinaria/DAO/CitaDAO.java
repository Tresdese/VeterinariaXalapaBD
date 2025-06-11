
package ClinicaVeterinaria.DAO;
import ClinicaVeterinaria.dto.CitaDTO;

import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class CitaDAO extends ConexionBD{
    private final static String SQL_INSERT = "INSERT INTO cita(fecha, hora, motivo, estatus, idMascota, idVeterianrio) VALUES (?, ?, ?, ?, ?, ?)";
    private final static String SQL_UPDATE = "UPDATE cita SET fecha=?, hora=?, motivo=?, estatus=?, idMascota=?, idVeterianrio=? WHERE idCita=?";
    private final static String SQL_DELETE = "DELETE FROM cita WHERE idCita=?";
    private final static String SQL_SELECT = "SELECT * FROM cita WHERE isCita=?";
    private final static String SQL_SELECTALL = "SELECT * FROM cita";
    
    public CitaDAO() throws Exception{
        super();
    }
    
     public void agregar(CitaDTO dto) throws Exception {
         PreparedStatement ps = null;
         try{
            ps = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            ps.setDate(1, java.sql.Date.valueOf(dto.getFecha()));
            LocalDateTime fechaHora = LocalDateTime.now().with(dto.getHora());
            ps.setTimestamp(2, java.sql.Timestamp.valueOf(fechaHora));
            ps.setString(3, dto.getMotivo());
            ps.setString(4, dto.getEstatus());
            ps.setDouble(5, dto.getIdMascota());
            ps.setInt(6, dto.getIdVeterinario());
            ps.executeUpdate();
            
            try(ResultSet generatedKeys = ps.getGeneratedKeys()){
                if(generatedKeys.next()){
                    dto.setIdCita(generatedKeys.getInt(1));
                }
            }
         }catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al agregar cita: " + e.getMessage());
        } finally {
            try {
                if (ps != null) ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
     }
     
     public void actualizar(CitaDTO dto) throws Exception {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(SQL_UPDATE);
            ps.setDate(1, java.sql.Date.valueOf(dto.getFecha()));
            LocalDateTime fechaHora = LocalDateTime.now().with(dto.getHora());
            ps.setTimestamp(2, java.sql.Timestamp.valueOf(fechaHora));
            ps.setString(3, dto.getMotivo());
            ps.setString(4, dto.getEstatus());
            ps.setDouble(5, dto.getIdMascota());
            ps.setInt(6, dto.getIdVeterinario());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al actualizar cita: " + e.getMessage());
        } finally {
            try {
                if (ps != null) ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void eliminar(CitaDTO dto) throws Exception {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(SQL_DELETE);
            ps.setInt(1, dto.getIdCita());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al eliminar cita: " + e.getMessage());
        } finally {
            try {
                if (ps != null) ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public CitaDTO buscar(CitaDTO dto) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(SQL_SELECT);
            ps.setInt(1, dto.getIdCita());
            rs = ps.executeQuery();
            if (rs.next()) {
                dto.setIdCita(rs.getInt("idCita"));
                java.sql.Date fechaSql = rs.getDate("fecha");
                dto.setFecha(fechaSql != null ? fechaSql.toLocalDate() : null);
                java.sql.Timestamp horaSql = rs.getTimestamp("hora");
                if(horaSql != null){
                    dto.setHora(horaSql.toLocalDateTime().toLocalTime());
                }else{
                    dto.setHora(null);
                }
                dto.setMotivo(rs.getString("motivo"));
                dto.setEstatus(rs.getString("estatus"));
                dto.setIdMascota(rs.getInt("idMascota"));
                dto.setIdVeterinario(rs.getInt("idVeterinario"));
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

    public List<CitaDTO> listarCompleto() throws Exception {
        List<CitaDTO> lista = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(SQL_SELECTALL);
            rs = ps.executeQuery();
            while (rs.next()) {
                java.sql.Date fechaSql = rs.getDate("fecha");
                LocalDate fecha = (fechaSql != null) ? fechaSql.toLocalDate() : null;
                
                java.sql.Timestamp horaSql = rs.getTimestamp("hora");
                LocalTime hora = (horaSql != null) ? horaSql.toLocalDateTime().toLocalTime() : null;
                
                CitaDTO dto = new CitaDTO(
                rs.getInt("idCita"),
                fecha,
                hora,
                rs.getString("motivo"),
                rs.getString("estatus"),
                rs.getInt("idMascota"),
                rs.getInt("idVeterinario"));
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
    
}
