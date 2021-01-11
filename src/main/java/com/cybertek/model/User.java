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

    @Column(unique = true,nullable = false)
    private String username;

    private String password;

    @Column(unique = true,nullable = false)
    private String email;

    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private ProductAndUserStatus status;

}
