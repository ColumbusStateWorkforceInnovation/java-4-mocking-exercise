package edu.cscc.jpaexercise.jpaexercise.services;

import edu.cscc.jpaexercise.jpaexercise.controllers.requests.CreateUserAddressRequest;
import edu.cscc.jpaexercise.jpaexercise.exceptions.ResourceNotFoundException;
import edu.cscc.jpaexercise.jpaexercise.models.User;
import edu.cscc.jpaexercise.jpaexercise.models.UserAddress;
import edu.cscc.jpaexercise.jpaexercise.repositories.UserAddressesRepository;
import edu.cscc.jpaexercise.jpaexercise.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserAddressesService {

    @Autowired
    private UserAddressesRepository userAddressesRepository;

    @Autowired
    private UsersRepository usersRepository;

    public UserAddressesService(UserAddressesRepository userAddressesRepository, UsersRepository usersRepository) {
        this.userAddressesRepository = userAddressesRepository;
        this.usersRepository = usersRepository;
    }

    public UserAddress createUserAddress(CreateUserAddressRequest createUserAddressRequest) {
        Optional<User> user = usersRepository.findById(createUserAddressRequest.userId());
        if (user.isEmpty()) {
            throw new ResourceNotFoundException("User not found");
        }
        UserAddress userAddress = new UserAddress(
                user.get(),
                createUserAddressRequest.street(),
                createUserAddressRequest.city(),
                createUserAddressRequest.state(),
                createUserAddressRequest.zip()
        );
        UserAddress newUserAddress = userAddressesRepository.save(userAddress);
        return newUserAddress;

    }
}
