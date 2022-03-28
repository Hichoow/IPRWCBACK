package nl.hsleiden.iprwc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service @RequiredArgsConstructor
@Transactional
public class AuthService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String encryptPassword(String password){
        return passwordEncoder.encode(password);
    }
}
