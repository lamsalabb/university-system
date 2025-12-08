package com.university.core.controller;

import com.university.common.entity.User;
import com.university.core.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userService.findAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable int id){
        try{
            User user = userService.findUserById(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        catch (RuntimeException e){
            return new ResponseEntity<>(
                    Map.of("message",e.getMessage()),
                    HttpStatus.NOT_FOUND
            );
        }
    }


    @PostMapping//Admin creation
    public ResponseEntity<?> createUser(@RequestBody User user){
        try{
            User User = userService.registerNewUser(user);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        }
        catch (RuntimeException e){
            return new ResponseEntity<>(
                    Map.of("message",e.getMessage()),
                    HttpStatus.CONFLICT
            );
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable int id, @RequestBody User userDetails){
        try {
            User updatedUser = userService.updateUser(id, userDetails);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        }
        catch (RuntimeException e){
            return new ResponseEntity<>(
                    Map.of("message",e.getMessage()),
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id){
        try {
            userService.deleteUser(id);
            return new ResponseEntity<>(
                    Map.of("message","User deleted Successfully."),
                    HttpStatus.OK
            );
        }
        catch (RuntimeException e){
            return new ResponseEntity<>(
                    Map.of("message",e.getMessage()),
                    HttpStatus.NOT_FOUND
            );
        }
    }



}
