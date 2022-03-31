package nl.hsleiden.iprwc.controller;

import nl.hsleiden.iprwc.DAO.Repository.RoleRepository;
import nl.hsleiden.iprwc.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/role")
public class RoleController {
    @Autowired
    private RoleRepository roleRepository;

    @PostMapping("/save")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public Role saveRole(Role role){
        return this.roleRepository.save(role);
    }
}
