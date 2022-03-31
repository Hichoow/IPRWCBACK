package nl.hsleiden.iprwc.model.response;

import java.util.List;

public class JwtRes {
    private String type = "Bearer";
    private String username;
    private String email;
    private String token;
    private Long id;
    private List<String> roles;

    public JwtRes(String username, String email, String token, Long id, List<String> roles) {
        this.username = username;
        this.email = email;
        this.token = token;
        this.id = id;
        this.roles = roles;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
