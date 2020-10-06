package nl.miwgroningen.cohort3.fortytwo.recipes.model;

import javax.persistence.*;


/**
 * @author Jasper Kelder, Nathalie Antoine, Reinout Smit, Jasmijn van der Veen
 * Cuisines are for example Italian Kitchen, French, Asian etc..
 */
@Entity
public class Cuisine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cuisineId;

    @Column(unique = true)
    private String cuisineName;

    // getters and setters
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
