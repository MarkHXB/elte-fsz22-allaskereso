package hu.elte.joooble.validation;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class LogoValidator implements ConstraintValidator<ValidLogo, MultipartFile> {

    @Override
    public void initialize(ValidLogo constraintAnnotation) {

    }

    @Override
    public boolean isValid(MultipartFile multipartFile, ConstraintValidatorContext context) {

        boolean result = true;

        String contentType = multipartFile.getContentType();
        if (!isSupportedContentType(contentType)) {
            result = false;
        }

        return result;
    }

    private boolean isSupportedContentType(String contentType) {
        return contentType.equals("image/jpg")
                || contentType.equals("image/jpeg")
                || contentType.equals("image/png");
    }
}
