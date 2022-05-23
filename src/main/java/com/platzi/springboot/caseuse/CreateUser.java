package com.platzi.springboot.caseuse;

import com.platzi.springboot.entity.User;
import com.platzi.springboot.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CreateUser {
    private UserService userService;

    public User save(User newUser) {
        return userService.save(newUser);
    }

}
