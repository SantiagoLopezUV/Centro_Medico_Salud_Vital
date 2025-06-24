package model;

public record MedicBasicInfo(long Id, String firstMiddlename, String lastName) {
    @Override
    public String toString() {
        return lastName + " " + firstMiddlename;
    }
}
