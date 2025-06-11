
package ClinicaVeterinaria.controller;

/**
 *
 * @author LEGION
 */

import ClinicaVeterinaria.DAO.DetalleVentaDAO;
import ClinicaVeterinaria.dto.DetalleVentaDTO;
import java.util.List;

public class DetalleVentaController {
    private DetalleVentaDAO dao;

    public DetalleVentaController() throws Exception {
        dao = new DetalleVentaDAO();
    }

    public void agregarDetalle(DetalleVentaDTO dto) throws Exception {
        dao.agregar(dto);
    }

    public void actualizarDetalle(DetalleVentaDTO dto) throws Exception {
        dao.actualizar(dto);
    }

    public void eliminarDetalle(DetalleVentaDTO dto) throws Exception {
        dao.eliminar(dto);
    }

    public DetalleVentaDTO buscarDetalle(int id) throws Exception {
        DetalleVentaDTO dto = new DetalleVentaDTO();
        dto.setIdDetalle(id);
        return dao.buscar(dto);
    }

    public List<DetalleVentaDTO> listarDetalles() throws Exception {
        return dao.listarCompleto();
    }
}

