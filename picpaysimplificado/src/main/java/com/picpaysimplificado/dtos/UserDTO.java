package com.picpaysimplificado.dtos;

import com.picpaysimplificado.domain.user.User;
import com.picpaysimplificado.domain.user.UserType;

import java.math.BigDecimal;

public record UserDTO(Long id,String firstname, String lastname, String document, String email, String password, BigDecimal balance, UserType userType) {

    public UserDTO(User user) {
        this(user.getId(), user.getFirstname(), user.getLastname(), user.getDocument(), user.getEmail(), user.getPassword(), user.getBalance(), user.getUserType());
    }

}
