package hu.elte.joooble.webdomain;

import hu.elte.joooble.validation.ValidPassword;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

public class EmployeeRegisterForm {
    @Email
    @NotBlank(message = "Email should not be empty!")
    private final String email;

    @ValidPassword
    private final String password;

    @NotBlank(message = "Birthdate should not be empty!")
    private final String birthday;

    public EmployeeRegisterForm(String email, String password, String birthday) {
        this.email = email;
        this.password = password;
        this.birthday = birthday;
    }
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getBirthday() { return birthday; }
}
