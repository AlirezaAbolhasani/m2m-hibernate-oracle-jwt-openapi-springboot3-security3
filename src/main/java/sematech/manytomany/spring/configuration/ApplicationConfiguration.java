package sematech.manytomany.spring.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import sematech.manytomany.spring.repository.UserRipo;

/**
 * @auteur ALireza Abolhasani
 * @date: 9/26/2024
 * @time: 4:01 PM
 * @mail: Abolhasany.Alireza@yahoo.com
 **/
@Configuration
public class ApplicationConfiguration {
    private final UserRipo userRepository;

    public ApplicationConfiguration(UserRipo userRepository) {
        this.userRepository = userRepository;
    }

//    The userDetailsService() defines how to retrieve the user using the UserRepository that is injected.
    @Bean
    UserDetailsService userDetailsService() {
        return username -> userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

//    The passwordEncoder() creates an instance of the BCryptPasswordEncoder() used to encode the plain user password.
    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    The authenticationProvider() sets the new strategy to perform the authentication.
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }
}

