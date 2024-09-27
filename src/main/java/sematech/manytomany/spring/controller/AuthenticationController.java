package sematech.manytomany.spring.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sematech.manytomany.spring.dtos.LoginUserDto;
import sematech.manytomany.spring.dtos.RegisterUserDto;
import sematech.manytomany.spring.model.LoginResponse;
import sematech.manytomany.spring.model.UserEntity;
import sematech.manytomany.spring.services.AuthenticationService;
import sematech.manytomany.spring.services.JwtService;

/**
 * @auteur ALireza Abolhasani
 * @date: 9/26/2024
 * @time: 5:53 PM
 * @mail: Abolhasany.Alireza@yahoo.com
 **/

@RequestMapping("/auth")
@RestController
public class AuthenticationController {

    private final JwtService jwtService;

    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<UserEntity> register(@RequestBody RegisterUserDto registerUserDto) {
        UserEntity registeredUser = authenticationService.signup(registerUserDto);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        UserEntity authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);loginResponse.setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }
}