package model;

import java.util.Objects;

public class Arrangement {
    private final int ArrangementCode; //codConvenio
    private final String corpName; //nomEmpresa
    private float percentage; //porcentaje
    private boolean valid; //vigente

    public Arrangement(int ArrangementCode, String corpName, float percentage, boolean valid) {
        if(ArrangementCode < 0
                || corpName == null
                || corpName.isEmpty()
                || percentage < 0
                || percentage > 1){
            throw new NullPointerException("Some Arrangement parameters are invalid");
        }

        this.ArrangementCode = ArrangementCode;
        this.corpName = corpName;
        this.percentage = percentage;
        this.valid = valid;
    }

    public int getArrangementCode() {
        return ArrangementCode;
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
        return ArrangementCode == that.ArrangementCode && Objects.equals(corpName, that.corpName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ArrangementCode, corpName);
    }
}
