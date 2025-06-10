package pl.edu.pja.s31719.constraints;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;
import pl.edu.pja.s31719.repositories.UserRepositorySpringData;

@Component
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {
    public UserRepositorySpringData userRepository;

    public UniqueEmailValidator(UserRepositorySpringData userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (userRepository.findByEmail(s).isPresent())
            return false;
        return true;
    }
}
