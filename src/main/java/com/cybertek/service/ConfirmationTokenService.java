package com.cybertek.service;

import com.cybertek.model.ConfirmationToken;
import org.springframework.mail.SimpleMailMessage;

public interface ConfirmationTokenService {

    void delete(Integer id);
    ConfirmationToken save(ConfirmationToken confirmationToken);
    ConfirmationToken readByToken(String token) throws Exception;
    void sendEmail(SimpleMailMessage simpleMailMessage);

}
