package ClinicaVeterinaria.controller;

/**
 *
 * @author LEGION
 */

import ClinicaVeterinaria.DAO.SecretariaDAO;
import ClinicaVeterinaria.dto.SecretariaDTO;
import java.util.List;

public class SecretariaController {
    private SecretariaDAO dao;

    public SecretariaController() throws Exception {
        dao = new SecretariaDAO();
    }

    public void agregarSecretaria(SecretariaDTO dto) throws Exception {
        dao.agregar(dto);
    }

    public void actualizarSecretaria(SecretariaDTO dto) throws Exception {
        dao.actualizar(dto);
    }

    public void eliminarSecretaria(SecretariaDTO dto) throws Exception {
        dao.eliminar(dto);
    }

    public SecretariaDTO buscarSecretaria(int id) throws Exception {
        SecretariaDTO dto = new SecretariaDTO();
        dto.setIdSecretaria(id);
        return dao.buscar(dto);
    }

    public List<SecretariaDTO> listarSecretarias() throws Exception {
        return dao.listarCompleto();
    }
}