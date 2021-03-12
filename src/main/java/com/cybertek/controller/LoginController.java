package com.cybertek.controller;

import com.cybertek.model.ConfirmationToken;
import com.cybertek.model.ResponseWrapper;
import com.cybertek.model.User;
import com.cybertek.model.common.AuthenticationRequest;
import com.cybertek.service.ConfirmationTokenService;
import com.cybertek.service.UserService;
import com.cybertek.util.JWTUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;
    private final UserService userService;
    private final ConfirmationTokenService confirmationTokenService;

    public LoginController(AuthenticationManager authenticationManager, JWTUtil jwtUtil, UserService userService, ConfirmationTokenService confirmationTokenService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
        this.confirmationTokenService = confirmationTokenService;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<ResponseWrapper> login(@RequestBody AuthenticationRequest authenticationRequest){

        String password = authenticationRequest.getPassword();
        String username = authenticationRequest.getUsername();


        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,password);
        authenticationManager.authenticate(authenticationToken);

        User foundUser = userService.readByUsername(username);
        String jwtToken = jwtUtil.generateToken(foundUser);

        return ResponseEntity.ok(new ResponseWrapper("Login Succesfull!",jwtToken));

    }

    @PostMapping("/create")
    public ResponseEntity<ResponseWrapper> createUser(@RequestBody User user) throws Exception {


        return ResponseEntity.ok(new ResponseWrapper("User has been created, please confirm the user!",
                userService.create(user)));
    }

    @GetMapping("/confirmation")
    public ResponseEntity<ResponseWrapper> confirmUser(@RequestParam("token") String token) throws Exception {
        ConfirmationToken confirmationToken = confirmationTokenService.readByToken(token);
        userService.confirmUser(confirmationToken.getUser());
        confirmationTokenService.delete(confirmationToken.getId());

        return ResponseEntity.ok(new ResponseWrapper("User has been confirmed successfully!",confirmationToken.getUser()));
    }


}
