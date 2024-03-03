package edu.cscc.jpaexercise.jpaexercise.controllers;

import edu.cscc.jpaexercise.jpaexercise.models.User;
import edu.cscc.jpaexercise.jpaexercise.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UsersRepository usersRepository;

    @GetMapping()
    public ResponseEntity<?> getUsers() {
        List<User> users = usersRepository.findAll();

        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
