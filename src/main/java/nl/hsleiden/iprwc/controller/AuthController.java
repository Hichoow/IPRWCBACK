package nl.hsleiden.iprwc.controller;


import nl.hsleiden.iprwc.DAO.Repository.RoleRepository;
import nl.hsleiden.iprwc.DAO.Repository.UserRepository;
import nl.hsleiden.iprwc.model.Role;
import nl.hsleiden.iprwc.model.User;
import nl.hsleiden.iprwc.model.request.LoginRequest;
import nl.hsleiden.iprwc.model.request.RegisterRequest;
import nl.hsleiden.iprwc.model.response.JwtRes;
import nl.hsleiden.iprwc.model.response.MsgRes;
import nl.hsleiden.iprwc.security.JWT.JwtUtil;
import nl.hsleiden.iprwc.security.service.UserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtil.genJwtToken(authentication);

        UserDetail userDetails = (UserDetail) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtRes(userDetails.getUsername(), userDetails.getEmail(), jwt, userDetails.getId(), roles));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest registerRequest) {

        if (userRepository.existsByUsername(registerRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MsgRes("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MsgRes("Error: Email is already in use!"));
        }

        // Create new user's account
        User user = new User(registerRequest.getUsername(), registerRequest.getEmail(), encoder.encode(registerRequest.getPassword()));

        Set<String> strRoles = registerRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName("ROLE_USER");
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName("ROLE_ADMIN");
                        roles.add(adminRole);

                        break;
                    case "mod":
                        Role modRole = roleRepository.findByName("ROLE_MODERATOR");
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName("ROLE_USER");
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MsgRes("User registered successfully!"));
    }
}

