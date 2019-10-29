package com.example.websocketdemo.config;
import com.example.websocketdemo.model.Role;
import org.springframework.stereotype.Component;
import org.springframework.context.ApplicationListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.websocketdemo.model.User;
//import com.example.websocketdemo.model.Role;
import com.example.websocketdemo.repository.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@Component
class Bootstrap implements ApplicationListener<ApplicationReadyEvent>{

    @Autowired
    UserRepository userRepository;

    Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        log.info("Verifying if default user exist");
        createUserWithRole("josdem", "12345678", "joseluis.delacruz@gmail.com", Role.USER);
    }

    private void createUserWithRole(String username, String password, String email, Role authority){
        List<User> list = userRepository.findAll();
        for(int i = 0; i < list.size(); i++)
        {
            log.info("" + (list.get(i).getUsername()));
        }
        if(userRepository.findByUsername(username) == null){
            log.info("Vdefault user doesnt exist");
            User user = new User(
                    username,
                    new BCryptPasswordEncoder().encode(password),
                    email,
                    authority,
                    username,
                    username,
                    true
      );
            userRepository.saveAndFlush(user);
        }
    }

}
