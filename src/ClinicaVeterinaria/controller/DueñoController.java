
package ClinicaVeterinaria.controller;

import ClinicaVeterinaria.DAO.DueñoDAO;
import ClinicaVeterinaria.dto.DueñoDTO;
import java.util.List;
/**
 *
 * @author LEGION
 */
public class DueñoController {
    private DueñoDAO dao;

    public DueñoController() throws Exception {
        dao = new DueñoDAO();
    }

    public void agregarDueno(DueñoDTO dto) throws Exception {
        dao.agregar(dto);
    }

    public void actualizarDueno(DueñoDTO dto) throws Exception {
        dao.actualizar(dto);
    }

    public void eliminarDueno(DueñoDTO dto) throws Exception {
        dao.eliminar(dto);
    }

    public DueñoDTO buscarDueno(int id) throws Exception {
        DueñoDTO dto = new DueñoDTO();
        dto.setIdCliente(id);
        return dao.buscar(dto);
    }

    public List<DueñoDTO> listarDuenos() throws Exception {
        return dao.listarCompleto();
    }
}

