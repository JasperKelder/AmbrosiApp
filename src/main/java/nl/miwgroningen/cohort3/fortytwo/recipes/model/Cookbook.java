package nl.miwgroningen.cohort3.fortytwo.recipes.model;
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
    private String cookbookName;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_Id", referencedColumnName = "userId", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "mycookbooks",
            joinColumns = @JoinColumn(
                    name = "cookbook_id", referencedColumnName = "cookbookId"),
            inverseJoinColumns = @JoinColumn(
                    name = "recipe_Id", referencedColumnName = "recipeId"))
    private List<Recipe> recipes;

    //Getters and Setters
    public Integer getCookbookId() { return cookbookId; }
    public void setCookbookId(Integer cookbookId) { this.cookbookId = cookbookId; }
    public String getCookbookName() { return cookbookName; }
    public void setCookbookName(String cookbookName) { this.cookbookName = cookbookName; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public List<Recipe> getRecipes() { return recipes; }
    public void setRecipes(List<Recipe> recipes) { this.recipes = recipes; }

}
