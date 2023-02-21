package hu.elte.joooble.webdomain;

import hu.elte.joooble.validation.ValidPassword;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

public class EmployerRegisterForm {
    @Email
    @NotBlank(message = "Email should not be empty!")
    private final String email;

    @ValidPassword
    private final String password;

    @NotBlank(message = "Company name should not be empty!")
    @Length(min = 3,max = 35,message = "Company name's length should be between 3 and 35.")
    private final String companyName;

    @NotBlank(message = "Company address should not be empty!")
    private final String companyAddress;

    public EmployerRegisterForm(String email, String password, String companyName, String companyAddress) {
        this.email = email;
        this.password = password;
        this.companyName = companyName;
        this.companyAddress = companyAddress;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }
}
