package nl.miwgroningen.cohort3.fortytwo.recipes.model;

import javax.persistence.*;
import java.util.List;

/**
 * @author Jasper Kelder, Nathalie Antoine, Reinout Smit, Jasmijn van der Veen
 */

@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roleId;

    private String name;

    @ManyToMany(mappedBy="roles")
    private List<User> users;

    //Constructors
    public Role() {}

    public Role(String name) {
        super();
        this.name = name;
    }

    //Getters and Setters
    public Integer getRoleId() {
        return roleId;
    }
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<User> getUsers() { return users; }
    public void setUsers(List<User> users) { this.users = users; }
}
