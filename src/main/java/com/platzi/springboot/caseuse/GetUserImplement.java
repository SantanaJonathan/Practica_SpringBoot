package com.platzi.springboot.caseuse;

import com.platzi.springboot.entity.User;
import com.platzi.springboot.services.UserService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;


@Getter
@Setter
//@ToString
@AllArgsConstructor
public class GetUserImplement implements GetUser{

    private UserService userService;

    @Override
    public List<User> getAll() {
        return userService.getAllUsers();
    }
}
