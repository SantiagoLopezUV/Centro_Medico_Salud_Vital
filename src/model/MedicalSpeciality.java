package model;

import java.util.Objects;

public class MedicalSpeciality {

    private final int specialtyCode; //codEspecialidad
    private String title; //titulo

    public MedicalSpeciality(int specialtyCode,
                             String title){
        if(specialtyCode < 0
                || title == null
                || title.isEmpty()){
            throw new NullPointerException("Some parameter(s) in medical speciality creation are missed!");
        }
        this.specialtyCode = specialtyCode;
        this.title = title;
    }

    public int getSpecialtyCode() {
        return specialtyCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MedicalSpeciality that)) return false;
        return specialtyCode == that.specialtyCode;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(specialtyCode);
    }
}
