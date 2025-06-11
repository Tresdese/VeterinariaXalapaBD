
package ClinicaVeterinaria.controller;

/**
 *
 * @author LEGION
 */

import ClinicaVeterinaria.DAO.VeterinarioDAO;
import ClinicaVeterinaria.dto.VeterinarioDTO;
import java.util.List;

public class VeterinarioController {
    private VeterinarioDAO dao;

    public VeterinarioController() throws Exception {
        dao = new VeterinarioDAO();
    }

    public void agregarVeterinario(VeterinarioDTO dto) throws Exception {
        dao.agregar(dto);
    }

    public void actualizarVeterinario(VeterinarioDTO dto) throws Exception {
        dao.actualizar(dto);
    }

    public void eliminarVeterinario(VeterinarioDTO dto) throws Exception {
        dao.eliminar(dto);
    }

    public VeterinarioDTO buscarVeterinario(int id) throws Exception {
        VeterinarioDTO dto = new VeterinarioDTO();
        dto.setIdVeterinario(id);
        return dao.buscar(dto);
    }

    public List<VeterinarioDTO> listarVeterinarios() throws Exception {
        return dao.listarCompleto();
    }
}

