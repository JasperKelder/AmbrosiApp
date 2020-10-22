package nl.miwgroningen.cohort3.fortytwo.recipes.model;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import javax.persistence.*;
import java.util.List;

/**
 * @author Jasper Kelder, Nathalie Antoine, Reinout Smit, Jasmijn van der Veen
 */

@Entity
public class Cookbook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cookbookId;

    @Column
    private boolean isPrivate;

    @Column
    private String cookbookName;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_Id", referencedColumnName = "userId", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE})
    @JoinTable(
            name = "mycookbooks",
            joinColumns = @JoinColumn(
                    name = "cookbook_id", referencedColumnName = "cookbookId"),
            inverseJoinColumns = @JoinColumn(
                    name = "recipe_Id", referencedColumnName = "recipeId"))
    private List<Recipe> recipes;

    //Constructors


    public Cookbook(boolean isPrivate, String cookbookName, User user) {
        this.isPrivate = isPrivate;
        this.cookbookName = cookbookName;
        this.user = user;
    }

    public Cookbook(boolean isPrivate, String cookbookName, User user, List<Recipe> recipes) {
        this.isPrivate = isPrivate;
        this.cookbookName = cookbookName;
        this.user = user;
        this.recipes = recipes;
    }

    public Cookbook() {
    }

    //Getters and Setters
    public Integer getCookbookId() { return cookbookId; }
    public void setCookbookId(Integer cookbookId) { this.cookbookId = cookbookId; }
    public String getCookbookName() { return cookbookName; }
    public void setCookbookName(String cookbookName) { this.cookbookName = cookbookName; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public List<Recipe> getRecipes() { return recipes; }
    public void setRecipes(List<Recipe> recipes) { this.recipes = recipes; }
    public boolean isPrivate() { return isPrivate; }
    public void setPrivate(boolean aPrivate) { isPrivate = aPrivate; }
}
