package nl.miwgroningen.cohort3.fortytwo.recipes.model;

import com.google.gson.annotations.Expose;

import javax.persistence.*;

@Entity
public class MeasuringUnit {

    @Expose
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer measuringUnitId;

    @Expose
    @Column(unique = true)
    private String measuringUnitName;

    @Expose
    @Column
    private String measuringUnitAbbreviation;

    // getters and setters
    public Integer getMeasuringUnitId() {
        return measuringUnitId;
    }
    public void setMeasuringUnitId(Integer measuringUnitId) {
        this.measuringUnitId = measuringUnitId;
    }
    public String getMeasuringUnitName() {
        return measuringUnitName;
    }
    public void setMeasuringUnitName(String measuringUnitName) {
        this.measuringUnitName = measuringUnitName;
    }
    public String getMeasuringUnitAbbreviation() {
        return measuringUnitAbbreviation;
    }
    public void setMeasuringUnitAbbreviation(String measuringUnitAbbreviation) {
        this.measuringUnitAbbreviation = measuringUnitAbbreviation;
    }
}
