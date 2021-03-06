package nl.miwgroningen.cohort3.fortytwo.recipes.dto;

/**
 * @author Jasper Kelder, Nathalie Antoine, Reinout Smit, Jasmijn van der Veen
 */

public class EmailChangeDto {

    private String emailAddress;

    //Constructors
    public EmailChangeDto(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    public EmailChangeDto() {};

    //Getters and Setters
    public String getEmailAddress() { return emailAddress; }
    public void setEmailAddress(String emailAddress) { this.emailAddress = emailAddress; }

}
