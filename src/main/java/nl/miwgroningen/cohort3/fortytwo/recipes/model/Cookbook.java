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
    @JoinColumn(name = "userId", referencedColumnName = "userId", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "my_cookbooks",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "userId"),
            inverseJoinColumns = @JoinColumn(
                    name = "cookbookId", referencedColumnName = "roleId"))
    private List<Role> cookbooks;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "my_recipes",
            joinColumns = @JoinColumn(
                    name = "cookbookId", referencedColumnName = "userId"),
            inverseJoinColumns = @JoinColumn(
                    name = "recipiId", referencedColumnName = "roleId"))
    private List<Role> recipes;

    //Getters and Setters
    public Integer getCookbookId() { return cookbookId; }

    public void setCookbookId(Integer cookbookId) { this.cookbookId = cookbookId; }

    public String getCookbookName() { return cookbookName; }

    public void setCookbookName(String cookbookName) { this.cookbookName = cookbookName; }

    public List<Role> getCookbooks() { return cookbooks; }

    public void setCookbooks(List<Role> cookbooks) { this.cookbooks = cookbooks; }

    public List<Role> getRecipes() { return recipes; }

    public void setRecipes(List<Role> recipes) { this.recipes = recipes; }

}
