package edu.cscc.jpaexercise.jpaexercise.services;

import edu.cscc.jpaexercise.jpaexercise.controllers.requests.CreateUserAddressRequest;
import edu.cscc.jpaexercise.jpaexercise.exceptions.ResourceNotFoundException;
import edu.cscc.jpaexercise.jpaexercise.models.User;
import edu.cscc.jpaexercise.jpaexercise.models.UserAddress;
import edu.cscc.jpaexercise.jpaexercise.repositories.UserAddressesRepository;
import edu.cscc.jpaexercise.jpaexercise.repositories.UsersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
class UserAddressesServiceTest {

    @Mock
    private UserAddressesRepository userAddressesRepository;

    @Mock
    private UsersRepository usersRepository;

    private UserAddressesService userAddressesService;

    @Captor
    private ArgumentCaptor<UserAddress> userAddressArgumentCaptor;

    @BeforeEach
    public void setup() {
        userAddressesService = new UserAddressesService(userAddressesRepository, usersRepository);
    }

    @Test
    @DisplayName("It can create a user address")
    public void itCanCreateAUserAddress() {
        CreateUserAddressRequest createUserAddressRequest =
                new CreateUserAddressRequest(
                        1, "123 Main St", "Columbus", "OH", "43215"
                );

        when(usersRepository.findById(createUserAddressRequest.userId())).thenReturn(Optional.of(new User()));
        when(userAddressesRepository.save(any(UserAddress.class))).thenReturn(new UserAddress());

        userAddressesService.createUserAddress(createUserAddressRequest);

        verify(usersRepository).findById(createUserAddressRequest.userId());
        verify(userAddressesRepository).save(userAddressArgumentCaptor.capture());

        assertEquals(createUserAddressRequest.street(), userAddressArgumentCaptor.getValue().getStreet());
        assertEquals(createUserAddressRequest.city(), userAddressArgumentCaptor.getValue().getCity());
        assertEquals(createUserAddressRequest.state(), userAddressArgumentCaptor.getValue().getState());
        assertEquals(createUserAddressRequest.zip(), userAddressArgumentCaptor.getValue().getZip());
    }

    @Test
    @DisplayName("It throws a ResourceNotFoundException when the user is not found")
    public void itThrowsAResourceNotFoundExceptionWhenTheUserIsNotFound() {
        CreateUserAddressRequest createUserAddressRequest =
                new CreateUserAddressRequest(
                        1, "123 Main St", "Columbus", "OH", "43215"
                );

        when(usersRepository.findById(createUserAddressRequest.userId())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> userAddressesService.createUserAddress(createUserAddressRequest));
    }
}