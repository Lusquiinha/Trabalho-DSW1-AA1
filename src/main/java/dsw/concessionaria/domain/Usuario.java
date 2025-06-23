package dsw.concessionaria.domain;

import dsw.concessionaria.enums.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import dsw.concessionaria.validation.UniqueUsername;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Usuario extends AbstractEntity<Long> {

    @UniqueUsername
    @Column(nullable = false, length = 20, unique = true)
    private String username;

    @NotBlank
    @Email
    @Column(nullable = false, length = 64)
    private String email;

    
    @Column(nullable = false, length = 64)
    private String password;

    @NotNull
    @Column(nullable = false)
    private UserRole role;


    public Usuario(@NotBlank String username, @NotBlank @Email String email, @NotBlank String password,
            @NotBlank UserRole role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public Usuario() {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Usuario [username=" + username + ", email=" + email + ", password=" + password + ", role=" + role + "]";
    }
}
