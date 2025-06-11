
package ClinicaVeterinaria.controller;

/**
 *
 * @author LEGION
 */

import ClinicaVeterinaria.DAO.ProductoDAO;
import ClinicaVeterinaria.dto.ProductoDTO;
import java.util.List;

public class ProductoController {
    private ProductoDAO dao;

    public ProductoController() throws Exception {
        dao = new ProductoDAO();
    }

    public void agregarProducto(ProductoDTO dto) throws Exception {
        dao.agregar(dto);
    }

    public void actualizarProducto(ProductoDTO dto) throws Exception {
        dao.actualizar(dto);
    }

    public void eliminarProducto(ProductoDTO dto) throws Exception {
        dao.eliminar(dto);
    }

    public ProductoDTO buscarProducto(int id) throws Exception {
        ProductoDTO dto = new ProductoDTO();
        dto.setIdProducto(id);
        return dao.buscar(dto);
    }

    public List<ProductoDTO> listarProductos() throws Exception {
        return dao.listarCompleto();
    }
}

