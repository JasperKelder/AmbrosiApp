package nl.miwgroningen.cohort3.fortytwo.recipes.model;

import javax.persistence.*;


/**
 * @author Jasper Kelder, Nathalie Antoine, Reinout Smit, Jasmijn van der Veen
 *
 * Category's are for example breakfast, lunch, dinner, snack
 */
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;

    @Column
    private String categoryName;

    //constructors
    public Category(String categoryName) {
        this.categoryName = categoryName;
    }
    public Category() {}

    //getters and setters
    public Integer getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
    public String getCategoryName() {
        return categoryName;
    }
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
