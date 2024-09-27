package sematech.manytomany.spring.services;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sematech.manytomany.spring.dtos.LoginUserDto;
import sematech.manytomany.spring.dtos.RegisterUserDto;
import sematech.manytomany.spring.model.UserEntity;
import sematech.manytomany.spring.repository.UserRipo;

/**
 * @auteur ALireza Abolhasani
 * @date: 9/26/2024
 * @time: 4:09 PM
 * @mail: Abolhasany.Alireza@yahoo.com
 **/
@Service
public class AuthenticationService {
    private final UserRipo userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
            UserRipo userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserEntity signup(RegisterUserDto input) {
        UserEntity user = new UserEntity();
                user.setName(input.getFullName());
                user.setEmail(input.getEmail());
                user.setPassword(passwordEncoder.encode(input.getPassword()));

        return userRepository.save(user);
    }

    public UserEntity authenticate(LoginUserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        return userRepository.findByEmail(input.getEmail())
                .orElseThrow();
    }
}