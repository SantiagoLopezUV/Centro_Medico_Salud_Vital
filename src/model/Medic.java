package model;

public class Medic extends Person{

    private Long professionalNumCard;
    private MedicalSpeciality medicalSpeciality;


    protected Medic(Long proNumCard, Long id, String firstMiddleName, String lastName, String telephoneNumber, String email, String address) {
        super(id, firstMiddleName, lastName, telephoneNumber, email, address);
        if(proNumCard==null
                ||proNumCard<999999){
            throw new NullPointerException("professional number card not valid");
        }
    }

    public Long getProfessionalNumCard() {
        return professionalNumCard;
    }

    public MedicalSpeciality getMedicalSpeciality() {
        return medicalSpeciality;
    }

    public void setMedicalSpeciality(MedicalSpeciality medicalSpeciality) {
        this.medicalSpeciality = medicalSpeciality;
    }
}
