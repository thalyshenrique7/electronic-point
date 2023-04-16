package com.devthalys.electronicpoint.controllers;

import com.devthalys.electronicpoint.dtos.CredentialsDto;
import com.devthalys.electronicpoint.dtos.TokenDto;
import com.devthalys.electronicpoint.exceptions.PasswordInvalidException;
import com.devthalys.electronicpoint.jwt.security.JwtService;
import com.devthalys.electronicpoint.models.UserModel;
import com.devthalys.electronicpoint.services.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserServiceImpl userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @PostMapping
//    @PreAuthorize("permitAll()")
    @ResponseStatus(CREATED)
    public UserModel save(@RequestBody @Valid UserModel user){
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        return userDetailsService.save(user);
    }

    @PostMapping("/auth")
//    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public TokenDto authenticated(@RequestBody CredentialsDto credentialsDto){
        try {
            UserModel user = UserModel
                    .builder()
                    .username(credentialsDto.getUsername())
                    .password(credentialsDto.getPassword())
                    .build();

            UserDetails userAuthenticated = userDetailsService.authenticated(user);
            String token = jwtService.generateToken(user);
            return new TokenDto(user.getUsername(), token);
        } catch (UsernameNotFoundException | PasswordInvalidException e){
            throw new ResponseStatusException(UNAUTHORIZED, e.getMessage());
        }
    }
}
