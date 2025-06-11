package ClinicaVeterinaria.dto;

public class SecretariaDTO {
    private int idSecretaria;
    private String nombre;
    private String apellidoP;
    private String apellidoM;
    private String numeroINE;
    private String telefono;
    private String dirSecretaria;
    private String nombreUsuario;
    private String contraseña;

    public SecretariaDTO() {}

    public SecretariaDTO(int idSecretaria, String nombre, String apellidoP, String apellidoM, String numeroINE, String telefono, String dirSecretaria, String nombreUsuario, String contraseña) {
        this.idSecretaria = idSecretaria;
        this.nombre = nombre;
        this.apellidoP = apellidoP;
        this.apellidoM = apellidoM;
        this.numeroINE = numeroINE;
        this.telefono = telefono;
        this.dirSecretaria = dirSecretaria;
        this.nombreUsuario = nombreUsuario;
        this.contraseña = contraseña;
    }

    public int getIdSecretaria() { return idSecretaria; }
    public void setIdSecretaria(int idSecretaria) { this.idSecretaria = idSecretaria; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellidoP() { return apellidoP; }
    public void setApellidoP(String apellidoP) { this.apellidoP = apellidoP; }

    public String getApellidoM() { return apellidoM; }
    public void setApellidoM(String apellidoM) { this.apellidoM = apellidoM; }

    public String getNumeroINE() { return numeroINE; }
    public void setNumeroINE(String numeroINE) { this.numeroINE = numeroINE; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getDirSecretaria() { return dirSecretaria; }
    public void setDirSecretaria(String dirSecretaria) { this.dirSecretaria = dirSecretaria; }

    public String getNombreUsuario() { return nombreUsuario; }
    public void setNombreUsuario(String nombreUsuario) { this.nombreUsuario = nombreUsuario; }

    public String getContraseña() { return contraseña; }
    public void setContraseña(String contraseña) { this.contraseña = contraseña; }
}