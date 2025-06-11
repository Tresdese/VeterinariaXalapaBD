
package ClinicaVeterinaria.logica.dto;


public class VentaDTO {
    private int idVenta;
    private String fecha;
    
    public VentaDTO(){}
    public VentaDTO(int idVenta, String fecha) {
        this.idVenta = idVenta;
        this.fecha = fecha;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
}
