package hu.elte.joooble.validation;

import javax.validation.*;

import hu.elte.joooble.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    @Autowired
    private UserService userService;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && !userService.isEmailAddressInUse(value);
    }

}
