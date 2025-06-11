package ClinicaVeterinaria.logica.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CitasAtendidasDAO {
    private ConexionBD conexionBD;

    public CitasAtendidasDAO() throws Exception {
        this.conexionBD = new ConexionBD();
    }

    public List<String[]> obtenerCitasAtendidas() throws Exception {
        List<String[]> citas = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT * FROM vista_citas_atendidas ORDER BY fecha_atencion DESC, hora_atencion DESC";
            pstmt = conexionBD.conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                String[] cita = new String[8];
                cita[0] = rs.getString("nombre_veterinario");
                cita[1] = rs.getString("nombre_mascota");
                cita[2] = rs.getString("nombre_propietario");
                cita[3] = rs.getString("motivo_consulta");
                cita[4] = rs.getString("diagnostico");
                cita[5] = rs.getString("tratamiento");
                cita[6] = rs.getDate("fecha_atencion").toString();
                cita[7] = rs.getTime("hora_atencion").toString();
                citas.add(cita);
            }
        } catch (SQLException e) {
            throw new Exception("Error al obtener citas atendidas: " + e.getMessage());
        } finally {
            conexionBD.cerrar(pstmt);
            if (rs != null) rs.close();
        }

        return citas;
    }

    public void mostrarCitasAtendidas() throws Exception {
        List<String[]> citas = obtenerCitasAtendidas();

        System.out.println("\n--- CITAS ATENDIDAS ---");
        System.out.printf("%-30s %-15s %-30s %-20s %-20s %-30s %-15s %-15s%n",
                "Veterinario", "Mascota", "Dueño", "Motivo", "Diagnóstico",
                "Tratamiento", "Fecha", "Hora");

        for (String[] cita : citas) {
            System.out.printf("%-30s %-15s %-30s %-20s %-20s %-30s %-15s %-15s%n",
                    cita[0], cita[1], cita[2], cita[3], cita[4], cita[5], cita[6], cita[7]);
        }
    }
}