package model;

import java.util.Objects;

public class ConsultationType {
    private final int idConsultationType; //codTipoCons
    private String consultationTypeName; //nomTipoCons
    private double consultationPrice; //costoConsulta

    public ConsultationType(int idConsultationType,
                            String consultationTypeName,
                            double consultationPrice) {
        if(idConsultationType < 0
                || consultationPrice < 0
                || consultationTypeName == null
                || consultationTypeName.isEmpty()) {
            throw new NullPointerException("Some consultation type parameters are wrong!");
        }

        this.idConsultationType = idConsultationType;
        this.consultationTypeName = consultationTypeName;
        this.consultationPrice = consultationPrice;
    }

    public int getIdConsultationType() {
        return idConsultationType;
    }

    public String getConsultationTypeName() {
        return consultationTypeName;
    }

    public double getConsultationPrice() {
        return consultationPrice;
    }

    public void setConsultationTypeName(String consultationTypeName) {
        this.consultationTypeName = consultationTypeName;
    }

    public void setConsultationPrice(double consultationPrice) {
        this.consultationPrice = consultationPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ConsultationType that)) return false;
        return idConsultationType == that.idConsultationType;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idConsultationType);
    }

    @Override
    public String toString() {
        return consultationTypeName;
    }
}
