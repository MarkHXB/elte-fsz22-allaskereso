package hu.elte.joooble.webdomain;

import hu.elte.joooble.validation.ValidLogo;
import hu.elte.joooble.validation.ValidPassword;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

public class EmployerEditForm {

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

    @Length(min = 3, max = 20,message = "The filename's length should be between 3 and 20.")
    @NotEmpty(message = "You should give a name to the file!")
    private final String filename;

    @ValidLogo
    private final MultipartFile file;

    public EmployerEditForm(String email, String password, String companyName, String companyAddress, String filename, MultipartFile file) {
        this.email = email;
        this.password = password;
        this.companyName = companyName;
        this.companyAddress = companyAddress;
        this.filename = filename;
        this.file = file;
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

    public String getFilename() {
        return filename;
    }

    public MultipartFile getFile() {
        return file;
    }
}
