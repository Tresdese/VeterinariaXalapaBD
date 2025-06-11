
package ClinicaVeterinaria.controller;

/**
 *
 * @author LEGION
 */

import ClinicaVeterinaria.DAO.MascotaDAO;
import ClinicaVeterinaria.dto.MascotaDTO;
import java.util.List;

public class MascotaController {
    private MascotaDAO dao;

    public MascotaController() throws Exception {
        dao = new MascotaDAO();
    }

    public void agregarMascota(MascotaDTO dto) throws Exception {
        dao.agregar(dto);
    }

    public void actualizarMascota(MascotaDTO dto) throws Exception {
        dao.actualizar(dto);
    }

    public void eliminarMascota(MascotaDTO dto) throws Exception {
        dao.eliminar(dto);
    }

    public MascotaDTO buscarMascota(int id) throws Exception {
        MascotaDTO dto = new MascotaDTO();
        dto.setIdMascota(id);
        return dao.buscar(dto);
    }

    public List<MascotaDTO> listarMascotas() throws Exception {
        return dao.listarCompleto();
    }
}
