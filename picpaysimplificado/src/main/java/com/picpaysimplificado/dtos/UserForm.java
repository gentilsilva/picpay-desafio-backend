package com.picpaysimplificado.dtos;

import com.picpaysimplificado.domain.user.User;
import com.picpaysimplificado.domain.user.UserType;

import java.math.BigDecimal;

public record UserForm(String firstname, String lastname, String document, String email, String password, BigDecimal balance, UserType userType) {
}
