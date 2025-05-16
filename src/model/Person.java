package model;

import java.util.Objects;

abstract class Person {
    protected final Long Id; //docIdentidad
    protected final String firstMiddleName; //nombres
    protected final String lastName; //apellidos
    protected String telephoneNumber; //telefono
    protected final SexType sex; //sexo
    protected String email; //email
    protected String address; //dirResidencia

    public enum SexType{
        MALE("Male"),
        FEMALE("Female"),
        OTHER("Other");

        final String value;

        SexType(String value) {
            this.value = value;
        }
        public String getValue() {
            return value;
        }
    }

    protected Person (Long id,
                     String firstMiddleName,
                     String lastName,
                     String telephoneNumber,
                     String email,
                     String address,
                     SexType sexType) {
        if(id == null
                || id <= 99999
                || firstMiddleName == null
                || lastName == null
                || telephoneNumber == null
                || email == null
                || address == null
                || sexType == null){
            throw new NullPointerException("Some basic parameters were null!");
        }

        this.Id = id;
        this.firstMiddleName = firstMiddleName;
        this.lastName = lastName;
        this.telephoneNumber = telephoneNumber;
        this.email = email;
        this.address = address;
        this.sex = sexType;
    }

    public Long getId() {
        return Id;
    }

    public String getFirstMiddleName() {
        return firstMiddleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public SexType getSex() {
        return sex;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person person)) return false;
        return Objects.equals(Id, person.Id) && Objects.equals(firstMiddleName, person.firstMiddleName) && Objects.equals(lastName, person.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, firstMiddleName, lastName);
    }
}
