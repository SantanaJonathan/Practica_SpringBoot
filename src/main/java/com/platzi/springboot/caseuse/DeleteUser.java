package com.platzi.springboot.caseuse;

import com.platzi.springboot.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DeleteUser {
    private UserService userService;


    public void remove(Long id) {
        userService.delete(id);
    }
}
