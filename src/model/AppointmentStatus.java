package model;

public enum AppointmentStatus {
    NULLIFIED("Anulado"), //ANULADO
    PAID("Pagada"), // PAGADA
    IN_PROGRESS("En curso"), //EN CURSO
    PAYMENT_REQUIRED("Pendiente por pago"), //PENDIENTE POR PAGO
    SCHEDULED("Agendada"), //AGENDADA
    MISSED_APPOINTMENT("Cita incumplida") // Cita incumplida
    ;


    private final String value;
    AppointmentStatus(String status) {
        this.value = status;
    }
    public String getValue() {
        return value;
    }
}
