package com.cybertek.service;

import com.cybertek.model.User;
import com.cybertek.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User create(User user) throws Exception {
        Optional<User> foundedUser = userRepository.findByUsername(user.getUsername());

        Optional<User> foundedUser2 = userRepository.findByEmail(user.getEmail());

        if (foundedUser.isPresent() || foundedUser2.isPresent()){
            throw new Exception("This user already exists");
        }

        return userRepository.save(user);
    }

    public List<User> readAll(){
        return userRepository.findAll();
    }

    public User readByUsername(String username){
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new NoSuchElementException("This user does not exist"));
    }

    public User readByEmail(String email){
        return userRepository.findByUsername(email)
                .orElseThrow(() -> new NoSuchElementException("This user does not exist"));
    }

    public void update(User user){
        userRepository.findByUsername(user.getUsername())
                .orElseThrow(() -> new NoSuchElementException("This user does not exist"));

        userRepository.save(user);
    }

}
