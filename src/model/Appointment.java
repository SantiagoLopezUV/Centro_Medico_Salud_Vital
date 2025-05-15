package model;
//  Clase de la entidad 'Cita'
public class Appointment {

    private int IdAppointment;
    private String status;

    public Appointment(int idAppointment, String status) {
        IdAppointment = idAppointment;
        this.status = status;
    }

    public int getIdAppointment() {
        return IdAppointment;
    }

    public void setIdAppointment(int idAppointment) {
        IdAppointment = idAppointment;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
