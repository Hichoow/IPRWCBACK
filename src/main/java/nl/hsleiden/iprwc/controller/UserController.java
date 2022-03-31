//package nl.hsleiden.iprwc.controller;
//
//import com.auth0.jwt.JWT;
//import com.auth0.jwt.JWTVerifier;
//import com.auth0.jwt.algorithms.Algorithm;
//import com.auth0.jwt.interfaces.DecodedJWT;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.RequiredArgsConstructor;
//import nl.hsleiden.iprwc.model.Role;
//import nl.hsleiden.iprwc.model.User;
//import nl.hsleiden.iprwc.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//import static org.springframework.http.HttpHeaders.AUTHORIZATION;
//import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
//
//@RestController
//@RequestMapping(value = "/users")
//@RequiredArgsConstructor
//@CrossOrigin("http://localhost:8080")
//public class UserController {
//
//    @Autowired
//    private UserService userService;
//
//
//
//    @GetMapping("")
//    @ResponseBody
//    @ResponseStatus(HttpStatus.OK)
//    public List<User> getUsers(){
//        return this.userService.getUsers();
//    }
//
//    @PostMapping("/save")
//    @ResponseBody
//    @ResponseStatus(HttpStatus.CREATED)
//    public User saveUser(User user){
//        return this.userService.create(user);
//    }
//
//    @PostMapping("/role/save")
//    @ResponseBody
//    @ResponseStatus(HttpStatus.CREATED)
//    public Role saveRole(Role role){
//        return this.userService.createRole(role);
//    }
//
//    @PostMapping("/role/addrole")
//    @ResponseBody
//    @ResponseStatus(HttpStatus.CREATED)
//    public Role addRoleToUser(Role role){
//        return this.userService.createRole(role);
//    }
//
//    @GetMapping("/token/refresh")
//    @ResponseBody
//    @ResponseStatus(HttpStatus.CREATED)
//    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        String authorizationHeader = request.getHeader(AUTHORIZATION);
//        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ") ){
//            try {
//                String refresh_token = authorizationHeader.substring("Bearer ".length());
//                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
//                JWTVerifier verifier = JWT.require(algorithm).build();
//                DecodedJWT decodedJWT = verifier.verify(refresh_token);
//                String username = decodedJWT.getSubject();
//                User user = userService.getUser(username);
//                String access_token = JWT.create()
//                        .withSubject(user.getUsername())
//                        .withExpiresAt(new Date(System.currentTimeMillis() + 10*60*1000))
//                        .withIssuer(request.getRequestURL().toString())
//                        .withClaim("roles", user.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
//                        .sign(algorithm);
//                Map<String, String> tokens = new HashMap<>();
//                tokens.put("access_token", access_token);
//                tokens.put("refresh_token", refresh_token);
//                response.setContentType(APPLICATION_JSON_VALUE);
//                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
//            } catch (Exception e){
//                response.setHeader("error", e.getMessage());
//                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
//                Map<String, String> error = new HashMap<>();
//                error.put("error_messaee", e.getMessage());
//                response.setContentType(APPLICATION_JSON_VALUE);
//                new ObjectMapper().writeValue(response.getOutputStream(), error);
//            }
//        }else{
//            throw new RuntimeException("Refresh token is missing");
//        }
//    }
//
//
//}
