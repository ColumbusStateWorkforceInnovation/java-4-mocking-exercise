package edu.cscc.jpaexercise.jpaexercise.controllers.requests;

public record CreateUserAddressRequest(
        Integer userId,
        String street,
        String city,
        String state,
        String zip
) {
}
