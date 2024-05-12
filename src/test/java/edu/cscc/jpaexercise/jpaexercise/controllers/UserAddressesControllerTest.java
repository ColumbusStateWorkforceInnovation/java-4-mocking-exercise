package edu.cscc.jpaexercise.jpaexercise.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import edu.cscc.jpaexercise.jpaexercise.controllers.requests.CreateUserAddressRequest;
import edu.cscc.jpaexercise.jpaexercise.models.User;
import edu.cscc.jpaexercise.jpaexercise.models.UserAddress;
import edu.cscc.jpaexercise.jpaexercise.services.UserAddressesService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application.test.properties"
)
public class UserAddressesControllerTest {

    @Autowired
    @MockBean
    private UserAddressesService userAddressesService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("It can create a user address")
    public void itCanCreateAUserAddress() throws Exception {
        CreateUserAddressRequest createUserAddressRequest =
                new CreateUserAddressRequest(
                        1, "123 Main St", "Columbus", "OH", "43215"
                );
        User user = new User();
        user.setId(1);
        UserAddress userAddress = new UserAddress(
                user,
                createUserAddressRequest.street(),
                createUserAddressRequest.city(),
                createUserAddressRequest.state(),
                createUserAddressRequest.zip()
        );
        when(userAddressesService.createUserAddress(createUserAddressRequest)).thenReturn(userAddress);

        mockMvc.perform(MockMvcRequestBuilders.post("/user-addresses")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(createUserAddressRequest)))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(userAddressesService).createUserAddress(createUserAddressRequest);
    }
}
