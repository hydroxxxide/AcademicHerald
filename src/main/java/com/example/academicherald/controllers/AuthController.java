package com.example.academicherald.controllers;


import com.example.academicherald.entity.User;
import com.example.academicherald.enums.UserRole;
import com.example.academicherald.requests.AuthenticationRequest;
import com.example.academicherald.requests.AuthenticationResponse;
import com.example.academicherald.requests.RegistrationRequest;
import com.example.academicherald.security.DetailsUser;
import com.example.academicherald.security.DetailsUserService;
import com.example.academicherald.services.UserService;
import com.example.academicherald.util.JWTUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final DetailsUserService userDetailsService;
    private final JWTUtil jwtUtil;


    public AuthController(UserService userService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, DetailsUserService userDetailsService, JWTUtil jwtUtil) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
    }


    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegistrationRequest registrationRequest) {
        if (userService.isPresentUsername(registrationRequest.getUsername())) {
            return ResponseEntity.badRequest().body("Username is already taken!");
        }

        User user = new User();
        user.setId(registrationRequest.getId());
        user.setUsername(registrationRequest.getUsername());
        user.setEmail(registrationRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
        user.setRole(UserRole.ROLE_GUEST);

        userService.create(user);

        return ResponseEntity.ok("User registered successfully!");
    }


    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect email or password");
        }
        final DetailsUser userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}
