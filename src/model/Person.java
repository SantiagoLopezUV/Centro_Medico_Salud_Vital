package model;

import java.util.Objects;

abstract class Person {
    protected Long Id;
    protected String firstMiddleName;
    protected String lastName;
    protected String telephoneNumber;
    protected String email;
    protected String address;

    protected Person(Long id, String firstMiddleName, String lastName, String telephoneNumber, String email, String address) {
        if(id == null
                || id <= 99999
                || firstMiddleName == null
                || lastName == null
                || telephoneNumber == null
                || email == null
                || address == null){
            throw new NullPointerException("Some basic parameters were null!");
        }

        this.Id = id;
        this.firstMiddleName = firstMiddleName;
        this.lastName = lastName;
        this.telephoneNumber = telephoneNumber;
        this.email = email;
        this.address = address;
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
