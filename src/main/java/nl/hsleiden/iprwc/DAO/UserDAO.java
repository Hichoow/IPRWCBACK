package nl.hsleiden.iprwc.DAO;

import nl.hsleiden.iprwc.DAO.Repository.RoleRepository;
import nl.hsleiden.iprwc.DAO.Repository.UserRepository;
import nl.hsleiden.iprwc.model.User;
import nl.hsleiden.iprwc.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDAO {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AuthService authService;

    public List<User> getUsers(){
        return this.userRepository.findAll();
    }

    public User create(User user){
        user.setPassword(this.authService.encryptPassword(user.getPassword()));
        return this.userRepository.save(user);
    }

    public User getUser(String username){
        return this.userRepository.findByUsername(username);
    }

    public void delete(User user){
        this.userRepository.delete(user);
    }
}
