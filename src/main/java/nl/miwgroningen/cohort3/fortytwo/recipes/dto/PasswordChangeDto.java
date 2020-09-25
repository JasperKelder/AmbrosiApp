package nl.miwgroningen.cohort3.fortytwo.recipes.dto;

/**
 * @author Jasper Kelder, Nathalie Antoine, Reinout Smit, Jasmijn van der Veen
 */

public class PasswordChangeDto {

    private String password;

    //Constructors
    public PasswordChangeDto(String password) {
        this.password = password;
    }
    public PasswordChangeDto() {};

    //Getters and Setters
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

}
