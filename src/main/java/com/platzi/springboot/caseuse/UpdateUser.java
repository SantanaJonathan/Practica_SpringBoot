package com.platzi.springboot.caseuse;

import com.platzi.springboot.entity.User;
import com.platzi.springboot.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UpdateUser {
    private UserService userService;


    public User update(User newUser, Long id) {
        return userService.update(newUser, id);
    }
}
