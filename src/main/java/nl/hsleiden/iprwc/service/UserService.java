package nl.hsleiden.iprwc.service;

import lombok.RequiredArgsConstructor;
import nl.hsleiden.iprwc.DAO.RoleDAO;
import nl.hsleiden.iprwc.DAO.UserDAO;
import nl.hsleiden.iprwc.model.Role;
import nl.hsleiden.iprwc.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service @RequiredArgsConstructor @Transactional
public class UserService {
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private RoleDAO roleDAO;

    public List<User> getUsers(){
        return this.userDAO.getUsers();
    }

    public void addRoleToUser(String username, String roleName){
        this.roleDAO.addRoleToUser(username, roleName);
    }

    public User create(User user){
        return this.userDAO.create(user);
    }

    public User getUser(String username){
        return this.userDAO.getUser(username);
    }

    public Role createRole(Role role){
        return this.roleDAO.createRole(role);
    }

    public void delete(User user){
        this.userDAO.delete(user);
    }
}
