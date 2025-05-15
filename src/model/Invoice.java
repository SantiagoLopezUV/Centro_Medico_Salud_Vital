package model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Invoice {

    private int invoiceNumber;  //   refFactura
    private LocalDate date; //  fecha
    private LocalTime time; //  hora
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

    public void setInvoiceNumber(int invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total += total;
    }

}
