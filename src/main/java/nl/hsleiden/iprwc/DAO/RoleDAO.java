package nl.hsleiden.iprwc.DAO;

import nl.hsleiden.iprwc.DAO.Repository.RoleRepository;
import nl.hsleiden.iprwc.DAO.Repository.UserRepository;
import nl.hsleiden.iprwc.model.Role;
import nl.hsleiden.iprwc.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleDAO {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;

    public Role createRole(Role role){
        return this.roleRepository.save(role);
    }

    public void addRoleToUser(String username, String roleName){
        User user = this.userRepository.findByUsername(username);
        Role role = this.roleRepository.findByName(roleName);
        user.getRoles().add(role);
    }

}
