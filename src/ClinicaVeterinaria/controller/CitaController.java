
package ClinicaVeterinaria.controller;

/**
 *
 * @author LEGION
 */

import ClinicaVeterinaria.DAO.CitaDAO;
import ClinicaVeterinaria.dto.CitaDTO;
import java.util.List;

public class CitaController {
    private CitaDAO dao;

    public CitaController() throws Exception {
        dao = new CitaDAO();
    }

    public void agregarCita(CitaDTO dto) throws Exception {
        dao.agregar(dto);
    }

    public void actualizarCita(CitaDTO dto) throws Exception {
        dao.actualizar(dto);
    }

    public void eliminarCita(CitaDTO dto) throws Exception {
        dao.eliminar(dto);
    }

    public CitaDTO buscarCita(int id) throws Exception {
        CitaDTO dto = new CitaDTO();
        dto.setIdCita(id);
        return dao.buscar(dto);
    }

    public List<CitaDTO> listarCitas() throws Exception {
        return dao.listarCompleto();
    }
}

