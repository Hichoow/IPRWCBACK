package nl.hsleiden.iprwc.service;

import lombok.RequiredArgsConstructor;
import nl.hsleiden.iprwc.DAO.RoleDAO;
import nl.hsleiden.iprwc.DAO.UserDAO;
import nl.hsleiden.iprwc.model.Role;
import nl.hsleiden.iprwc.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service @RequiredArgsConstructor @Transactional
public class UserService implements UserDetailsService {
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private RoleDAO roleDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userDAO.getUser(username);
        if (user == null){
            throw new UsernameNotFoundException("User not found");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

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
