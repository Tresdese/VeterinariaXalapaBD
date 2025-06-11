
package ClinicaVeterinaria.dto;
import java.time.LocalDate;
import java.time.LocalTime;

public class CitaDTO {
    private int idCita;
    private LocalDate fecha;
    private LocalTime hora;        
    private String motivo;        
    private String estatus;       
    private int idMascota;    
    private int idVeterinario;
    
    public CitaDTO(){}
    public CitaDTO(int idCita, LocalDate fecha, LocalTime hora, String motivo, String estatus, int idMascota, int idVeterinario) {
        this.idCita = idCita;
        this.fecha = fecha;
        this.hora = hora;
        this.motivo = motivo;
        this.estatus = estatus;
        this.idMascota = idMascota;
        this.idVeterinario = idVeterinario;
    }

    public int getIdCita() {
        return idCita;
    }

    public void setIdCita(int idCita) {
        this.idCita = idCita;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public int getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(int idMascota) {
        this.idMascota = idMascota;
    }

    public int getIdVeterinario() {
        return idVeterinario;
    }

    public void setIdVeterinario(int idVeterinario) {
        this.idVeterinario = idVeterinario;
    }
    
    
}
