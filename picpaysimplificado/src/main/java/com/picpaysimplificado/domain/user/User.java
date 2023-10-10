package com.picpaysimplificado.domain.user;

import com.picpaysimplificado.dtos.UserForm;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity(name = "users")
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private String lastname;
    @Column(unique = true)
    private String document;
    @Column(unique = true)
    private String email;
    private String password;
    private BigDecimal balance;
    @Enumerated(EnumType.STRING)
    private UserType userType;

    public User(UserForm userForm) {
        this.firstname = userForm.firstname();
        this.lastname = userForm.lastname();
        this.document = userForm.document();
        this.email = userForm.email();
        this.password = userForm.password();
        this.balance = userForm.balance();
        this.userType = userForm.userType();
    }
}
