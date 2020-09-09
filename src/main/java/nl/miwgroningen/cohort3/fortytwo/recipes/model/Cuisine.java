package nl.miwgroningen.cohort3.fortytwo.recipes.model;

import javax.persistence.*;

// Cuisines are for example Italian Kitchen, French, Asian etc..

/**
 * @author Jasper Kelder, Nathalie Antoine, Reinout Smit, Jasmijn van der Veen
 */
@Entity
public class Cuisine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cuisineId;

    @Column(unique = true)
    private String cuisineName;

    @Override
    public String toString() {
        return cuisineName;
    }

    public Integer getCuisineId() {
        return cuisineId;
    }

    public void setCuisineId(Integer cuisineId) {
        this.cuisineId = cuisineId;
    }

    public String getCuisineName() {
        return cuisineName;
    }

    public void setCuisineName(String cuisineName) {
        this.cuisineName = cuisineName;
    }
}
