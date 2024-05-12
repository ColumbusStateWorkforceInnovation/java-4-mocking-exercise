package edu.cscc.jpaexercise.jpaexercise.services;

import edu.cscc.jpaexercise.jpaexercise.repositories.UserAddressesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserAddressesService {

    @Autowired
    private UserAddressesRepository userAddressesRepository;

    public UserAddressesService(UserAddressesRepository userAddressesRepository) {
        this.userAddressesRepository = userAddressesRepository;
    }
}
