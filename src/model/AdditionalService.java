package model;

public class AdditionalService {

    private int IdAdditionalService;    //
    private String nameAdditionalService;
    private double costAdditionalService;

    public AdditionalService(int idAdditionalService, String nameAdditionalService, double priceAdditionalService) {
        IdAdditionalService = idAdditionalService;
        this.nameAdditionalService = nameAdditionalService;
        this.costAdditionalService = priceAdditionalService;
    }
    public int getIdAdditionalService() {
        return IdAdditionalService;
    }
    public void setIdAdditionalService(int idAdditionalService) {
        IdAdditionalService = idAdditionalService;
    }
    public String getNameAdditionalService() {
        return nameAdditionalService;
    }
    public void setNameAdditionalService(String nameAdditionalService) {
        this.nameAdditionalService = nameAdditionalService;
    }
    public double getCostAdditionalService() {
        return costAdditionalService;
    }
    public void setCostAdditionalService(double costAdditionalService) {
        this.costAdditionalService = costAdditionalService;
    }

}
