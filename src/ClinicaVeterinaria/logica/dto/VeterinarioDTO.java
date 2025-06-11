
package ClinicaVeterinaria.logica.dto;

public class VeterinarioDTO {
    private int idVeterinario;
    private String nombre;
    private String apellidoP;
    private String apellidoM;
    private String cedulaProf;
    private String telefono;
    private String dirVet;
    private String nombreUsuario;
    private String contraseña;

    public VeterinarioDTO(int idVeterinario, String nombre, String apellidoP, String apellidoM, String cedulaProf, String telefono, String dirVet, String nombreUsuario, String contraseña) {
        this.idVeterinario = idVeterinario;
        this.nombre = nombre;
        this.apellidoP = apellidoP;
        this.apellidoM = apellidoM;
        this.cedulaProf = cedulaProf;
        this.telefono = telefono;
        this.dirVet = dirVet;
        this.nombreUsuario = nombreUsuario;
        this.contraseña = contraseña;
    }

    public int getIdVeterinario() {
        return idVeterinario;
    }

    public void setIdVeterinario(int idVeterinario) {
        this.idVeterinario = idVeterinario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoP() {
        return apellidoP;
    }

    public void setApellidoP(String apellidoP) {
        this.apellidoP = apellidoP;
    }

    public String getApellidoM() {
        return apellidoM;
    }

    public void setApellidoM(String apellidoM) {
        this.apellidoM = apellidoM;
    }

    public String getCedulaProf() {
        return cedulaProf;
    }

    public void setCedulaProf(String cedulaProf) {
        this.cedulaProf = cedulaProf;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDirVet() {
        return dirVet;
    }

    public void setDirVet(String dirVet) {
        this.dirVet = dirVet;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
    
    public VeterinarioDTO(){}
    

}
