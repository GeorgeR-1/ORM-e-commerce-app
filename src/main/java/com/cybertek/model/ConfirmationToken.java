package com.cybertek.model;

import com.cybertek.enums.TokenType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "confirmation_email")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Where(clause = "is_deleted=false")
public class ConfirmationToken extends BaseEntity<Integer>{

    private String token;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    private LocalDate expireDate;

    @Enumerated(EnumType.STRING)
    private TokenType tokenType;

    public Boolean isTokenValid(LocalDate date){
        LocalDate now = LocalDate.now();
        return date.isEqual(now) || date.isEqual(now.plusDays(1));
    }

    public ConfirmationToken(User user,TokenType tokenType){
        this.user = user;
        expireDate = LocalDate.now().plusDays(1);
        token = UUID.randomUUID().toString();
        this.tokenType = tokenType;
    }


}
