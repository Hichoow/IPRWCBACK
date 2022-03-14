package nl.hsleiden.iprwc.controller;

import lombok.RequiredArgsConstructor;
import nl.hsleiden.iprwc.model.Role;
import nl.hsleiden.iprwc.model.User;
import nl.hsleiden.iprwc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1")
@RequiredArgsConstructor
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<User> getUsers(){
        return this.userService.getUsers();
    }

    @PostMapping("/user/save")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public User saveUser(User user){
        return this.userService.create(user);
    }

    @PostMapping("/role/save")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public Role saveRole(Role role){
        return this.userService.createRole(role);
    }

    @PostMapping("/role/addrole")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public Role addRoleToUser(Role role){
        return this.userService.createRole(role);
    }


}
