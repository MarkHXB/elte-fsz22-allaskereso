package hu.elte.joooble.webdomain.authentication;

import hu.elte.joooble.validation.ValidPassword;
import org.springframework.lang.NonNull;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class LoginForm {
    @NonNull
    @NotBlank(message="Email should not be empty")
    @Email
    private String username;

    @NonNull
    private String password;

    public LoginForm(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(@NonNull String username) {
        this.username = username;
    }

    public void setPassword(@NonNull String password) {
        this.password = password;
    }
}
