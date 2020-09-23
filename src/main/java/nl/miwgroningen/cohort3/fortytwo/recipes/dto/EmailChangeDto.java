package nl.miwgroningen.cohort3.fortytwo.recipes.dto;

public class EmailChangeDto {

    private String emailAddress;

    //Constructors
    public EmailChangeDto(String emailAddress) {
        super();
        this.emailAddress = emailAddress;
    }

    public EmailChangeDto() {};

    //Getters and Setters
    public String getEmailAddress() { return emailAddress; }
    public void setEmailAddress(String emailAddress) { this.emailAddress = emailAddress; }
}
