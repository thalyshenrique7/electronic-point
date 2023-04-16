package com.devthalys.electronicpoint.services.impl;

import com.devthalys.electronicpoint.exceptions.PasswordInvalidException;
import com.devthalys.electronicpoint.exceptions.UserNotFoundException;
import com.devthalys.electronicpoint.models.UserModel;
import com.devthalys.electronicpoint.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel user = userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User " + username +  " not found in data base"));

        String[] roles = user.isAdmin() ? new String[]{"ADMIN", "USER"} : new String[]{"USER"};

        return User
                .builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(roles)
                .build();
    }

    @Transactional
    public UserModel save(UserModel user){
        return userRepository.save(user);
    }

    public UserDetails authenticated(UserModel user){
        UserDetails userDetails = loadUserByUsername(user.getUsername());
        boolean passwordEquals = encoder.matches(user.getPassword(), userDetails.getPassword());

            if(passwordEquals){
                return userDetails;
            }

            throw new PasswordInvalidException("Password is invalid");
    }
}
