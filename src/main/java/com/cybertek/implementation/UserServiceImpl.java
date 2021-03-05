package com.cybertek.implementation;

import com.cybertek.enums.ProductAndUserStatus;
import com.cybertek.enums.TokenType;
import com.cybertek.model.ConfirmationToken;
import com.cybertek.model.User;
import com.cybertek.model.dto.MailDTO;
import com.cybertek.repository.UserRepository;
import com.cybertek.service.ConfirmationTokenService;
import com.cybertek.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Value("${app.local-url}")
    String url;

    private final UserRepository userRepository;
    private final ConfirmationTokenService confirmationTokenService;

    public UserServiceImpl(UserRepository userRepository, ConfirmationTokenService confirmationTokenService) {
        this.userRepository = userRepository;
        this.confirmationTokenService = confirmationTokenService;
    }

    @Transactional
    public User create(User user) throws Exception {
        Optional<User> foundedUser = userRepository.findByUsername(user.getUsername());

        Optional<User> foundedUser2 = userRepository.findByEmail(user.getEmail());

        if (foundedUser.isPresent() || foundedUser2.isPresent()){
            throw new Exception("This user already exists");
        }

        User userCreated = userRepository.save(user);

        createToken(userCreated,TokenType.CONFIRMED);

        return userCreated;
    }

    public List<User> readAll(){
        return userRepository.findAll(Sort.by("id"));
    }

    public User readByUsername(String username){
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new NoSuchElementException("This user does not exist"));
    }

    public User readByEmail(String email){
        return userRepository.findByUsername(email)
                .orElseThrow(() -> new NoSuchElementException("This user does not exist"));
    }

    @Transactional
    public void update(User user){
        User foundedUser =userRepository.findByUsername(user.getUsername())
                .orElseThrow(() -> new NoSuchElementException("This user does not exist"));

//        if(!foundedUser.getEmail().equals(user.getEmail())){
//            //TODO check for confirmation
//        }

        user.setId(foundedUser.getId());

        userRepository.save(user);
    }

    @Transactional
    public void deactivateAccount(Long id) throws Exception {

        User foundedUser = userRepository.findById(id)
                .orElseThrow(() -> new Exception("user does not exist"));

        foundedUser.setStatus(ProductAndUserStatus.SUSPENDED);
        userRepository.save(foundedUser);

    }

    private void createToken(User user, TokenType tokenType){

        ConfirmationToken confirmationToken = new ConfirmationToken(user, tokenType);

        ConfirmationToken token = confirmationTokenService.save(confirmationToken);

        MailDTO mailDTO = MailDTO.builder()
                .emailTo(user.getEmail())
                .message("To confirm this account please click here: ")
                .token(token.getToken())
                .url(url + "/confirmation?token=")
                .subject("Confirm your account")
                .build();

        sendEmail(mailDTO);

    }

    private void sendEmail(MailDTO mailDTO){

        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setTo(mailDTO.getEmailTo());
        mailMessage.setSubject(mailDTO.getSubject());
        mailMessage.setText(mailDTO.getMessage() + mailDTO.getUrl() + mailDTO.getToken());

        confirmationTokenService.sendEmail(mailMessage);
    }


}
