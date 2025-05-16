package model;
//  Clase de la entidad 'Factura'
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class Invoice {

    private final int invoiceNumber;  //   refFactura
    private final LocalDate date; //  fecha
    private final LocalTime time; //  hora
    private double total;   //  valorTotal

    public Invoice(int invoiceNumber, LocalDate date, LocalTime time, double total) {
        this.invoiceNumber = invoiceNumber;
        this.date = date;
        this.time = time;
        this.total = total;
    }

    public int getInvoiceNumber() {
        return invoiceNumber;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Invoice invoice)) return false;
        return invoiceNumber == invoice.invoiceNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(invoiceNumber);
    }
}
