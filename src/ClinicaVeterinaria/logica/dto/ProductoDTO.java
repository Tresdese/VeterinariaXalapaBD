
package ClinicaVeterinaria.logica.dto;
    
public class ProductoDTO {
    private int idProducto;
    private String nombre;
    private String tipo;
    private String especie;
    private String marca;
    private float precio;
    private int existencia;
    
    public ProductoDTO(){}
    public ProductoDTO(int idProducto, String nombre, String tipo, String especie, String marca, float precio, int existencia) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.tipo = tipo;
        this.especie = especie;
        this.marca = marca;
        this.precio = precio;
        this.existencia = existencia;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getExistencia() {
        return existencia;
    }

    public void setExistencia(int existencia) {
        this.existencia = existencia;
    }
    
}
