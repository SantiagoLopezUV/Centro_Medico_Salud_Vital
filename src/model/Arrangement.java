package model;

import java.util.Objects;

public class Arrangement {
    private int codConvenio;
    private String corpName;
    private float percentage;
    private boolean valid;

    public Arrangement(int codConvenio, String corpName, float percentage, boolean valid) {
        if(codConvenio < 0
                || corpName == null
                || corpName.isEmpty()
                || percentage < 0
                || percentage > 1){
            throw new NullPointerException("Some Arrangement parameters are invalid");
        }

        this.codConvenio = codConvenio;
        this.corpName = corpName;
        this.percentage = percentage;
        this.valid = valid;
    }

    public int getCodConvenio() {
        return codConvenio;
    }

    public String getCorpName() {
        return corpName;
    }

    public float getPercentage() {
        return percentage;
    }

    public boolean isValid() {
        return valid;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Arrangement that)) return false;
        return codConvenio == that.codConvenio && Objects.equals(corpName, that.corpName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codConvenio, corpName);
    }
}
