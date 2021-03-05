package com.cybertek.implementation;

import com.cybertek.model.ConfirmationToken;
import com.cybertek.repository.ConfirmationTokenRepository;
import com.cybertek.service.ConfirmationTokenService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class ConfirmationTokenServiceImpl implements ConfirmationTokenService {

    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final JavaMailSender javaMailSender;

    public ConfirmationTokenServiceImpl(ConfirmationTokenRepository confirmationTokenRepository, JavaMailSender javaMailSender) {
        this.confirmationTokenRepository = confirmationTokenRepository;
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void delete(Integer id) {
        confirmationTokenRepository.deleteById(id);
    }

    @Override
    public ConfirmationToken save(ConfirmationToken confirmationToken) {
        return confirmationTokenRepository.save(confirmationToken);
    }

    @Override
    public ConfirmationToken readByToken(String token) throws Exception {
        ConfirmationToken confirmationToken = confirmationTokenRepository.findByToken(token)
                .orElseThrow(() -> new Exception("This token does not exist or is broken"));


        if (!confirmationToken.isTokenValid(confirmationToken.getExpireDate())){
            throw new Exception("This token is expired");
        }

        return confirmationToken;
    }

    @Override
    public void sendEmail(SimpleMailMessage simpleMailMessage) {

        javaMailSender.send(simpleMailMessage);

    }
}
