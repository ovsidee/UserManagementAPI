package pl.edu.pja.s31719.dto.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import pl.edu.pja.s31719.constraints.UniqueEmail;
import pl.edu.pja.s31719.constraints.UniqueUsername;

public class UserRequestDTO {
    @UniqueUsername
    @NotBlank
    public String username;

    @NotBlank
    public String password;

    @UniqueEmail
    @NotBlank
    @Email
    public String email;

    public UserRequestDTO() {}

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}
