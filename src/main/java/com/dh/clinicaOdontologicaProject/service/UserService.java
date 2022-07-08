package com.dh.clinicaOdontologicaProject.service;
import com.dh.clinicaOdontologicaProject.entity.User;
import com.dh.clinicaOdontologicaProject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

//@Service
public class UserService {
        //implements UserDetailsService {

    /*private final UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userSearch = userRepository.findByUserName(username);
        if(userSearch.isPresent()){
            return userSearch.get();
        } else {
            throw new UsernameNotFoundException("Username not found in our database.");
        }
    }*/
}
