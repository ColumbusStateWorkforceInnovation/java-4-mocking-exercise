package edu.cscc.jpaexercise.jpaexercise.controllers;

import edu.cscc.jpaexercise.jpaexercise.controllers.requests.CreateUserAddressRequest;
import edu.cscc.jpaexercise.jpaexercise.exceptions.ResourceNotFoundException;
import edu.cscc.jpaexercise.jpaexercise.models.User;
import edu.cscc.jpaexercise.jpaexercise.models.UserAddress;
import edu.cscc.jpaexercise.jpaexercise.repositories.UserAddressesRepository;
import edu.cscc.jpaexercise.jpaexercise.repositories.UsersRepository;
import edu.cscc.jpaexercise.jpaexercise.services.UserAddressesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user-addresses")
public class UserAddressesController {

    @Autowired
    private UserAddressesRepository userAddressesRepository;

    @Autowired
    private UserAddressesService userAddressesService;

    @Autowired
    private UsersRepository usersRepository;

    @GetMapping("/{id}/user")
    public ResponseEntity<?> getUser(@PathVariable Integer id) {
        Optional<UserAddress> maybeUserAddress = userAddressesRepository.findById(id);
        if (maybeUserAddress.isEmpty()) {
            throw new ResourceNotFoundException("User address not found");
        }

        UserAddress userAddress = maybeUserAddress.get();
        User user = usersRepository.findByUserAddresses(userAddress);

        return ResponseEntity.ok(user);
    }

    @PostMapping()
    public ResponseEntity<?> create(@Valid @RequestBody CreateUserAddressRequest createUserAddressRequest) {
        UserAddress userAddress = userAddressesService.createUserAddress(createUserAddressRequest);
        return ResponseEntity.ok(userAddress);
    }
}
