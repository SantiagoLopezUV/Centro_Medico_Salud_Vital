package model;

public class Patient extends Person{
    private Arrangement arrangement;
    public Patient(long id,
                   String firstMiddleName,
                   String lastName,
                   String telephoneNumber,
                   String email,
                   String address,
                   SexType sex,
                   Arrangement arrangement) {
        super(id, firstMiddleName, lastName, telephoneNumber, email, address, sex);
        this.arrangement = arrangement;
    }

    public Arrangement getArrangement() {
        return arrangement;
    }

    public void setArrangement(Arrangement arrangement) {
        this.arrangement = arrangement;
    }
}
