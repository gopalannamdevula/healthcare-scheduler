package com.gopal.patient.controller;

import com.gopal.patient.config.JwtUtil;
import com.gopal.patient.model.Users;
import com.gopal.patient.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;


    @PostMapping("/register")
    public ResponseEntity<String>  register(@RequestBody Users user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<String>  login(@RequestBody Users user){
        //new UsernamePasswordAuthenticationToken(...)
        //Creates a login token using the username and password provided by the client.
        //
        //authenticationManager.authenticate(...)
        //Passes this token to Spring Security’s internal authentication system.
        //
        //Authentication Flow:
        //
        //It triggers your custom UserDetailsService (in your case, MyUserDetailService) to load user data from the DB.
        //
        //Then it compares the provided password with the one stored in DB (using the configured PasswordEncoder).
        //
        //If credentials are valid, it returns a fully authenticated Authentication object (which contains user details and roles).
        //
        //If invalid, it throws BadCredentialsException.
        //
        //Success → You can generate a JWT.
        //Failure → Spring throws an exception automatically.
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword())
        );
        Users userdb  = userRepository.findByUsername(user.getUsername()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        String token = jwtUtil.generateToken((UserDetails)authentication.getPrincipal(),userdb.getId());
        return ResponseEntity.ok(token);
    }
    @GetMapping("/getById/{id}")
    public boolean getUserId(@PathVariable String id){
         return userRepository.existsById(id);
    }
}
