package model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

//  Clase de la entidad 'Cita'
public class Appointment {

    private final int IdAppointment; //codCita
    private AppointmentStatus status; //estado
    private final Long patientId; //pacienteId
    private final Long medicId; //medicoId
    private LocalDate appointmentDate; //fechaCita
    private LocalTime appointmentTime; //horaCita
    private final int consultationId; //codTipoCons
    private final double consultationRegisteredPrice; //costoConsReg
    private int invoiceNumber; //refFactura
    private final int arrangementCode; //codConvenio
    private final double arrangementDiscountApplied; //porcentajeAplicado

    public Appointment(int idAppointment,
                       AppointmentStatus status,
                       Long patientId,
                       Long medicId,
                       LocalDate appointmentDate,
                       LocalTime appointmentTime,
                       int consultationId,
                       double consultationRegisteredPrice,
                       int arrangementCode,
                       double arrangementDiscountApplied) {
        if(idAppointment < 0
                || status == null
                || patientId == null
                || patientId < 99999
                || medicId == null
                || medicId < 99999
                || appointmentDate == null
                || appointmentDate.isBefore(LocalDate.now())
                || appointmentTime == null
                || appointmentTime.isBefore(LocalTime.now())
                || consultationId < 0
                || consultationRegisteredPrice < 0
                || arrangementCode < 0
                || arrangementDiscountApplied < 0) {
            throw new NullPointerException("Some appointment fields are missing");

        }

        IdAppointment = idAppointment;
        this.status = status;
        this.patientId = patientId;
        this.medicId = medicId;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.consultationId = consultationId;
        this.consultationRegisteredPrice = consultationRegisteredPrice;
        this.arrangementCode = arrangementCode;
        this.arrangementDiscountApplied = arrangementDiscountApplied;
    }

    public int getIdAppointment() {
        return IdAppointment;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public Long getPatientId() {
        return patientId;
    }

    public Long getMedicId() {
        return medicId;
    }

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public LocalTime getAppointmentTime() {
        return appointmentTime;
    }

    public int getConsultationId() {
        return consultationId;
    }

    public double getConsultationRegisteredPrice() {
        return consultationRegisteredPrice;
    }

    public int getInvoiceNumber() {
        return invoiceNumber;
    }

    public int getArrangementCode() {
        return arrangementCode;
    }

    public double getArrangementDiscountApplied() {
        return arrangementDiscountApplied;
    }

    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public void setAppointmentTime(LocalTime appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public void setInvoiceNumber(int invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Appointment that)) return false;
        return IdAppointment == that.IdAppointment;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(IdAppointment);
    }
}
