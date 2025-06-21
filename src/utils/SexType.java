package utils;

public enum SexType{
    MALE("Masculino"),
    FEMALE("Femenino");

    final String value;

    SexType(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}
