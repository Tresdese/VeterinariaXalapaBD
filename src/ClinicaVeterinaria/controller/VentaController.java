
package ClinicaVeterinaria.controller;

/**
 *
 * @author LEGION
 */

import ClinicaVeterinaria.DAO.VentaDAO;
import ClinicaVeterinaria.dto.VentaDTO;
import java.util.List;

public class VentaController {
    private VentaDAO dao;

    public VentaController() throws Exception {
        dao = new VentaDAO();
    }

    public void agregarVenta(VentaDTO dto) throws Exception {
        dao.agregar(dto);
    }

    public void actualizarVenta(VentaDTO dto) throws Exception {
        dao.actualizar(dto);
    }

    public void eliminarVenta(VentaDTO dto) throws Exception {
        dao.eliminar(dto);
    }

    public VentaDTO buscarVenta(int id) throws Exception {
        VentaDTO dto = new VentaDTO();
        dto.setIdVenta(id);
        return dao.buscar(dto);
    }

    public List<VentaDTO> listarVentas() throws Exception {
        return dao.listarCompleto();
    }
}

