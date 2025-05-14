package model;

public class MedicalSpeciality {

    private int specialtyCode;
    private String title;

    public MedicalSpeciality(int specialtyCode, String title) {
        this.specialtyCode = specialtyCode;
        this.title = title;
    }

    public int getSpecialtyCode() {
        return specialtyCode;
    }

    public void setSpecialtyCode(int specialtyCode) {
        this.specialtyCode = specialtyCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
