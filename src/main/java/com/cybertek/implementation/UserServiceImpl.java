package com.cybertek.implementation;

import com.cybertek.enums.ProductAndUserStatus;
import com.cybertek.model.User;
import com.cybertek.repository.UserRepository;
import com.cybertek.service.UserService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User create(User user) throws Exception {
        Optional<User> foundedUser = userRepository.findByUsername(user.getUsername());

        Optional<User> foundedUser2 = userRepository.findByEmail(user.getEmail());

        if (foundedUser.isPresent() || foundedUser2.isPresent()){
            throw new Exception("This user already exists");
        }

        return userRepository.save(user);
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


}
