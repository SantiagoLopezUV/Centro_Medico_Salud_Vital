package model;

import java.time.LocalDate;
import java.time.LocalTime;

//  Clase de la entidad 'Cita'
public class Appointment {

    private int IdAppointment;
    private AppointmentStatus status;
    private Long patientId;
    private Long medicId;
    private LocalDate appointmentDate;
    private LocalTime appointmentTime;
    private int consultationId;
    private double consultationRegisteredPrice;
    private int invoiceNumber;
    private int arrangementCode;
    private double arrangementDiscountApplied;

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
                || consultationRegisteredPrice < 0) {

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
}
