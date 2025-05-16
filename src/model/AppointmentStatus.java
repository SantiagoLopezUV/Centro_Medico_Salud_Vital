package model;

public enum AppointmentStatus {
    NULLIFIED("Nullified"), //ANULADO
    PAID("Paid"), // PAGADA
    IN_PROGRESS("In Progress"), //EN CURSO
    PAYMENT_REQUIRED("Payment Required"), //PENDIENTE POR PAGO
    ;


    private final String value;
    AppointmentStatus(String status) {
        this.value = status;
    }
    public String getValue() {
        return value;
    }
}
