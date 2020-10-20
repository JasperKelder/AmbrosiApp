package nl.miwgroningen.cohort3.fortytwo.recipes.model;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import javax.persistence.*;
import java.util.Set;

/**
 * @author Jasper Kelder, Nathalie Antoine, Reinout Smit, Jasmijn van der Veen
 */

@Entity
public class Recipe {

    @Expose
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer recipeId;

    @Column
    private String recipeTitle;

    @Column
    // preptime will be set in minutes
    private Integer preperationTime;

    @Column
    private Integer servings;

    @Expose
    @OneToMany(mappedBy="recipe", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private Set<RecipeIngredient> recipeIngredients;


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

    @Lob
    private byte[] image;

    // constructors
    public Recipe(byte[] image, Category categoryName) {
        this.image = image;
    }
    public Recipe() { }

    //getters and setters
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
    public Set<RecipeIngredient> getRecipeIngredients() {
        return recipeIngredients;
    }
    public void setRecipeIngredients(Set<RecipeIngredient> recipeIngredients) {
        this.recipeIngredients = recipeIngredients;
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
    public byte[] getImage() {
        return image;
    }
    public void setImage(byte[] image) {
        this.image = image;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
}
