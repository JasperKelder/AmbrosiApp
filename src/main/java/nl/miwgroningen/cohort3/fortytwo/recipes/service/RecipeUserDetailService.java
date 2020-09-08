package nl.miwgroningen.cohort3.fortytwo.recipes.service;

import nl.miwgroningen.cohort3.fortytwo.recipes.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class RecipeUserDetailService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return (UserDetails) userRepository.findByUsername(s).orElseThrow(
                () -> new UsernameNotFoundException("User " + s + " was not found")
        );
    }
}
