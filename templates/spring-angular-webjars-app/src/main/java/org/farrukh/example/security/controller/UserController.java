package org.farrukh.example.security.controller;

import org.farrukh.example.security.domain.User;
import org.farrukh.example.security.domain.UserDTO;
import org.farrukh.example.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/")
    public String home() {
        return "Welcome to home";
    }

    @RequestMapping(value = "/sign_up", method = RequestMethod.POST)
    public ResponseEntity<User> createUser(@RequestBody final UserDTO userDTO) {
        User user = userService.create(userDTO);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/users", method = RequestMethod.PUT)
    public ResponseEntity<User> updateUser(@RequestBody final UserDTO userDTO) {
        User user = userService.update(userDTO);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<Collection<User>> getUsers() {
        Collection<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id)
                               .orElseThrow(() -> new UsernameNotFoundException("User by: " + id + "not found"));
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/users/{email}", method = RequestMethod.GET)
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        User user = userService.getUserByEmail(email)
                               .orElseThrow(() -> new UsernameNotFoundException("User by: " + email + "not found"));
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
