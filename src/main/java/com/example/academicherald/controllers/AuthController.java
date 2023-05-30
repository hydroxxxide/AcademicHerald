package com.example.academicherald.controllers;


import com.example.academicherald.enums.UserRole;
import com.example.academicherald.models.User;
import com.example.academicherald.requests.AuthenticationRequest;
import com.example.academicherald.requests.AuthenticationResponse;
import com.example.academicherald.requests.RegistrationRequest;
import com.example.academicherald.services.UserService;
import com.example.academicherald.util.JWTUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JWTUtil jwtUtil;


    public AuthController(UserService userService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, UserDetailsService userDetailsService, JWTUtil jwtUtil) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
    }


    @PostMapping("/register")
    @ResponseBody
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


    @PostMapping(value = "/authenticate")
    @ResponseBody
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect user or password");
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }


    @GetMapping("/login/oauth2")
    public String redirectToAuthorizationEndpoint() {
        return "redirect:/login/oauth2/code/github";
    }

    @GetMapping("/login/oauth2/code/github")
    @ResponseBody
    public ResponseEntity<?> handleGitLabCallback(@RequestParam("code") String code, OAuth2AuthenticationToken authenticationToken) {
        DefaultOAuth2User oauth2User = (DefaultOAuth2User) authenticationToken.getPrincipal();

        // Get the user's name attribute, if available
        String userName = oauth2User.getAttribute("name");
        System.out.println();
        if (userName == null) {
            // Handle the case when the name attribute is null
            // You can choose to set a default name or return an error response
            return ResponseEntity.badRequest().body("User name attribute is missing");
        }

        // Perform necessary actions with the user's name
        return ResponseEntity.ok("GitHub OAuth2 Login successful for user: " + userName);
    }
}
