package nl.miwgroningen.cohort3.fortytwo.recipes.dto;

public class PasswordChangeDto {

    private String password;

    //Constructors
    public PasswordChangeDto(String password) { this.password = password; }
    public PasswordChangeDto() {};

    //Getters and Setters
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

}
