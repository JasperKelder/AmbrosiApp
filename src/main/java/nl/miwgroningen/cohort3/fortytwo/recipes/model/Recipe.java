package nl.miwgroningen.cohort3.fortytwo.recipes.model;

import javax.persistence.*;

/**
 * @author Jasper Kelder, Nathalie Antoine, Reinout Smit, Jasmijn van der Veen
 */
@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer recipeId;

    @Column(columnDefinition = "text")
    private String recipeTitle;

    @Column(columnDefinition = "text")
    private String recipeType;

    @Column(columnDefinition = "text")
    private String recipeInfo;

    public Integer getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Integer recipeId) {
        this.recipeId = recipeId;
    }

    public String getRecipeTitle() {
        return recipeTitle;
    }

    public void setRecipeTitle(String recipeTitle) {
        this.recipeTitle = recipeTitle;
    }

    public String getRecipeType() {
        return recipeType;
    }

    public void setRecipeType(String recipeType) {
        this.recipeType = recipeType;
    }

    public String getRecipeInfo() {
        return recipeInfo;
    }

    public void setRecipeInfo(String recipeInfo) {
        this.recipeInfo = recipeInfo;
    }
}
