package com.auction.validation.user;

import com.auction.dto.user.UserDto;
import com.auction.exception.UserCannotBeCreated;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolation;
import javax.validation.ValidatorFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserValidator {

    private final ValidatorFactory validatorFactory;

    public void validateCreate(UserDto userDto) {
        Set<String> errors = validateCommonAnnotations(userDto);

        Set<String> firstNameErrors = checkThatUserHaveFirstName(userDto);
        if (!firstNameErrors.isEmpty()) {
            errors.addAll(firstNameErrors);
        }

        Set<String> lastNameErrors = checkThatUserHaveLastName(userDto);
        if (!lastNameErrors.isEmpty()) {
            errors.addAll(lastNameErrors);
        }

        Set<String> emailErrors = checkThatUserHaveEmail(userDto);
        if (!emailErrors.isEmpty()) {
            errors.addAll(emailErrors);
        }

        if (!errors.isEmpty()) {
            throw new UserCannotBeCreated(errors);
        }
    }

    private Set<String> validateCommonAnnotations(UserDto userDto) {
        return validatorFactory.getValidator()
                               .validate(userDto)
                               .stream()
                               .map(ConstraintViolation::getMessage)
                               .collect(Collectors.toSet());
    }

    public Set<String> checkThatUserHaveFirstName(UserDto userDto) {
        if (userDto.getFirstName() == null) {
            return Collections.singleton("Input your firstname.");
        }
        return Collections.emptySet();
    }

    public Set<String> checkThatUserHaveLastName(UserDto userDto) {
        if (userDto.getLastName() == null) {
            return Collections.singleton("Input your lastname.");
        }
        return Collections.emptySet();
    }

    public Set<String> checkThatUserHaveEmail(UserDto userDto) {
        if (userDto.getEmail() == null) {
            return Collections.singleton("Input your email.");
        }
        return Collections.emptySet();
    }
}
