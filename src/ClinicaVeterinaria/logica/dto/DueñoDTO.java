
package ClinicaVeterinaria.logica.dto;

public class DueñoDTO {
    private int idCliente;
    private String  apellido_M;
    private String apellido_P;
    private String nombre;
    private String numDirCliente;
    private String telefono;
    private String email;

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getApellido_M() {
        return apellido_M;
    }

    public void setApellido_M(String apellido_M) {
        this.apellido_M = apellido_M;
    }

    public String getApellido_P() {
        return apellido_P;
    }

    public void setApellido_P(String apellido_P) {
        this.apellido_P = apellido_P;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNumDirCliente() {
         return numDirCliente;
    }

    public void setNumDirCliente(String numDirCliente) {
        this.numDirCliente = numDirCliente;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
    
}
