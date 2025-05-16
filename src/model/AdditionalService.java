package model;

import java.util.Objects;

//  Clase de la entidad 'Servicio_Adicional'
public class AdditionalService {

    private final int additionalServiceId;    //codServAdi
    private String nameAdditionalService; //nomServAdi
    private double costAdditionalService; //valorServAdiActual

    public AdditionalService(int additionalServiceId, String nameAdditionalService, double priceAdditionalService) {
        this.additionalServiceId = additionalServiceId;
        this.nameAdditionalService = nameAdditionalService;
        this.costAdditionalService = priceAdditionalService;
    }

    public int getAdditionalServiceId() {
        return additionalServiceId;
    }

    public String getNameAdditionalService() {
        return nameAdditionalService;
    }

    public double getCostAdditionalService() {
        return costAdditionalService;
    }

    public void setNameAdditionalService(String nameAdditionalService) {
        this.nameAdditionalService = nameAdditionalService;
    }

    public void setCostAdditionalService(double costAdditionalService) {
        this.costAdditionalService = costAdditionalService;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AdditionalService that)) return false;
        return additionalServiceId == that.additionalServiceId;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(additionalServiceId);
    }
}
