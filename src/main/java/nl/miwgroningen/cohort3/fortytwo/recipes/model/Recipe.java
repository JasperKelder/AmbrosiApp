package nl.miwgroningen.cohort3.fortytwo.recipes.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

/**
 * @author Jasper Kelder, Nathalie Antoine, Reinout Smit, Jasmijn van der Veen
 */
@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer recipeId;

    @Column
    private String recipeTitle;

    @Column
    private String recipePreperation;

    @Column
    // preptime will be set in minutes
    private Integer preperationTime;

    @Column
    private Integer servings;

    @Column
    private String ingredients;

    @Column
    // Cooktime will be set in minutes
    private Integer cooktime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cuisineId", referencedColumnName = "cuisineId", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Cuisine cuisineName;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "categoryId", referencedColumnName = "categoryId", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Category categoryName;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "userId", referencedColumnName = "userId", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

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

    public String getRecipePreperation() {
        return recipePreperation;
    }

    public void setRecipePreperation(String recipePreperation) {
        this.recipePreperation = recipePreperation;
    }

    public Integer getPreperationTime() {
        return preperationTime;
    }

    public void setPreperationTime(Integer preperationTime) {
        this.preperationTime = preperationTime;
    }

    public Integer getServings() {
        return servings;
    }

    public void setServings(Integer servings) {
        this.servings = servings;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public Integer getCooktime() {
        return cooktime;
    }

    public void setCooktime(Integer cooktime) {
        this.cooktime = cooktime;
    }

    public Cuisine getCuisineName() {
        return cuisineName;
    }

    public void setCuisineName(Cuisine cuisineName) {
        this.cuisineName = cuisineName;
    }

    public Category getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(Category categoryName) {
        this.categoryName = categoryName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User userName) {
        this.user = userName;
    }
}
