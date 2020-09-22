package nl.miwgroningen.cohort3.fortytwo.recipes.model;

import javax.persistence.*;
import java.util.List;

/**
 * @author Jasper Kelder, Nathalie Antoine, Reinout Smit, Jasmijn van der Veen
 */

@Entity
@Table(name = "user", uniqueConstraints = @UniqueConstraint(columnNames = "email_address"))
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email_address")
    private String emailAddress;

    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "userId"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "roleId"))
    private List<Role> roles;

    //Constructors
    public User() { }

    public User(String firstName, String lastName, String emailAddress, String password, List < Role > roles) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.password = password;
        this.roles = roles;
    }

    public User(String encode) {

    }


    //Used in view.html to display the user who added the recipe
    @Override
    public String toString() {
        return firstName;
    }

    //Getters and Setters
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getEmailAddress() {
        return emailAddress;
    }
    public void setEmailAddress(String email) {
        this.emailAddress = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public List < Role > getRoles() {
        return roles;
    }
    public void setRoles(List < Role > roles) {
        this.roles = roles;
    }

}
