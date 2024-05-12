package edu.cscc.jpaexercise.jpaexercise.services;

import edu.cscc.jpaexercise.jpaexercise.repositories.UserAddressesRepository;
import edu.cscc.jpaexercise.jpaexercise.repositories.UsersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
class UserAddressesServiceTest {

    @Mock
    private UserAddressesRepository userAddressesRepository;

    @Mock
    private UsersRepository usersRepository;

    private UserAddressesService userAddressesService;

    @BeforeEach
    public void setup() {
        userAddressesService = new UserAddressesService(userAddressesRepository, usersRepository);
    }
}