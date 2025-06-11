
package ClinicaVeterinaria.dto;


public class DetalleVentaDTO {
    private int idDetalle;
    private int cantidad;
    private double subTotal;
    private int idVenta;
    private int idProducto;
    
    public DetalleVentaDTO(){}
    public DetalleVentaDTO(int idDetalle, int cantidad, double subTotal, int idVenta, int idProducto) {
        this.idDetalle = idDetalle;
        this.cantidad = cantidad;
        this.subTotal = subTotal;
        this.idVenta = idVenta;
        this.idProducto = idProducto;
    }

    public int getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(int idDetalle) {
        this.idDetalle = idDetalle;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }
    
    
}
