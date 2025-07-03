package model;
//  Clase de la entidad 'Factura'
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class Invoice {

    private final long invoiceNumber;  //   refFactura
    private final Date date; //  fecha
    private final Time time; //  hora
    private double total;   //  valorTotal

    public Invoice(long invoiceNumber, Date date, Time time, double total) {
        this.invoiceNumber = invoiceNumber;
        this.date = date;
        this.time = time;
        this.total = total;
    }

    public long getInvoiceNumber() {
        return invoiceNumber;
    }

    public Date getDate() {
        return date;
    }

    public Time getTime() {
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
