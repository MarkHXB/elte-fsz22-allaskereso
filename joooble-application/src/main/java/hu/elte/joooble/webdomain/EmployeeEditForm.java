package hu.elte.joooble.webdomain;

import hu.elte.joooble.validation.ValidPassword;
import hu.elte.joooble.validation.ValidResume;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

public class EmployeeEditForm {

    @Email
    @NotBlank(message = "Email should not be empty!")
    private final String email;

    @ValidPassword
    private final String password;

    @Length(min = 3, max = 20,message = "The filename's length should be between 3 and 20.")
    @NotEmpty(message = "You should give a name to the file.")
    private final String filename;

    @ValidResume
    private final MultipartFile file;

    public EmployeeEditForm(String email, String password, String filename, MultipartFile file) {
        this.email = email;
        this.password = password;
        this.filename = filename;
        this.file = file;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFilename() {
        return filename;
    }

    public MultipartFile getFile() {
        return file;
    }
}
