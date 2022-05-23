package com.platzi.springboot.controller;

import com.platzi.springboot.caseuse.CreateUser;
import com.platzi.springboot.caseuse.DeleteUser;
import com.platzi.springboot.caseuse.GetUser;
import com.platzi.springboot.caseuse.UpdateUser;
import com.platzi.springboot.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserRestController {

    //create,get,delete,update
    private GetUser getUser;
    private CreateUser createUser;

    private DeleteUser deleteUser;

    private UpdateUser updateUser;

    @GetMapping("/")
    List<User> get(){
        return getUser.getAll();
    }

    @PostMapping("/")
    ResponseEntity<User> newUser(@RequestBody User newUser){
        return new ResponseEntity<>(createUser.save(newUser), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    ResponseEntity deleteUser(@PathVariable Long id){
        deleteUser.remove(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    ResponseEntity<User> replaceUser(@RequestBody User newUser, @PathVariable Long id){
        return new ResponseEntity<>(updateUser.update(newUser, id), HttpStatus.OK);
    }
}

