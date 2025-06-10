package pl.edu.pja.s31719.constraints;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;
import pl.edu.pja.s31719.repositories.UserRepositorySpringData;

@Component
public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {
    public UserRepositorySpringData userRepository;

    public UniqueUsernameValidator(UserRepositorySpringData userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (userRepository.findByUsername(s).isPresent())
            return false;
        return true;
    }
}
