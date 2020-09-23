package nl.miwgroningen.cohort3.fortytwo.recipes.service;

import nl.miwgroningen.cohort3.fortytwo.recipes.dto.EmailChangeDto;
import nl.miwgroningen.cohort3.fortytwo.recipes.dto.PasswordChangeDto;
import nl.miwgroningen.cohort3.fortytwo.recipes.dto.UserRegistrationDto;
import nl.miwgroningen.cohort3.fortytwo.recipes.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.security.Principal;

public interface UserService extends UserDetailsService {

    User save(UserRegistrationDto registrationDto);

    User save(PasswordChangeDto passwordChangeDto, Principal principal);

    User saveNewEmailadress(EmailChangeDto emailChangeDto, Principal principal);

}
