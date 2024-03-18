package edu.cscc.jpaexercise.jpaexercise.controllers;

import edu.cscc.jpaexercise.jpaexercise.models.User;
import edu.cscc.jpaexercise.jpaexercise.models.UserAddress;
import edu.cscc.jpaexercise.jpaexercise.repositories.UserAddressesRepository;
import edu.cscc.jpaexercise.jpaexercise.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/user-addresses")
public class UserAddressesController {

    @Autowired
    private UserAddressesRepository userAddressesRepository;

    @Autowired
    private UsersRepository usersRepository;

    @GetMapping("/{id}/user")
    public ResponseEntity<?> getUser(@PathVariable Integer id) {
        Optional<UserAddress> maybeUserAddress = userAddressesRepository.findById(id);
        if (maybeUserAddress.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        UserAddress userAddress = maybeUserAddress.get();
        User user = usersRepository.findByUserAddresses(userAddress);

        return ResponseEntity.ok(user);
    }
}
