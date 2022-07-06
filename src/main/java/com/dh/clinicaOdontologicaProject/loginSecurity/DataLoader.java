package com.dh.clinicaOdontologicaProject.loginSecurity;

import com.dh.clinicaOdontologicaProject.entity.User;
import com.dh.clinicaOdontologicaProject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
//para agg datos a la DB cuando arranca el server
public class DataLoader implements ApplicationRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String passwordHash = passwordEncoder.encode("passwordPepe");
        User user1 = new User("Pepe", "pepe_argento", "pepe@mail.com", passwordHash, AppUserRoles.ROLE_USER);

        userRepository.save(user1);
    }
}
