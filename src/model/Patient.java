package model;

public class Patient extends Person{

    public Patient(long id,
                   String firstMiddleName,
                   String lastName,
                   String telephoneNumber,
                   String email,
                   String address,
                   SexType sex) {
        super(id, firstMiddleName, lastName, telephoneNumber, email, address, sex);
    }
}
