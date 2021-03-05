package com.cybertek.model;

import com.cybertek.enums.ProductAndUserStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
public class User extends BaseEntity<Long>{


    private String firstName;


    private String lastName;


    private LocalDate birthDate;

    @Column(unique = true)
    private String username;


    private String password;

    @Column(unique = true)
    private String email;


    private String phoneNumber;

    @Column(name = "is_verified")
    private Boolean isVerified;

    @Enumerated(EnumType.STRING)
    private ProductAndUserStatus status;

}
