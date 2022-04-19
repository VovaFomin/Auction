package com.auction.validation.lot;

import com.auction.dto.lot.LotDto;
import com.auction.exception.LotCannotBeCreatedOrChangedException;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolation;
import javax.validation.ValidatorFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LotValidator {
    private static final String LOT_INVALID_START_TIME_MSG =
        "Invalid Start Time. The Start Time should be not earlier than current time.";
    private static final String LOT_INVALID_END_TIME_MSG =
        "Invalid End Time. The End Time should be not earlier than current time.";

    private final ValidatorFactory validatorFactory;
    private final DateTimeUtils dateTimeUtils;

    public void validateCreate(LotDto lotDto) {
        Set<String> errors = validateCommonAnnotations(lotDto);
        if (lotDto.getLotDateTime() != null) {
            LocalDateTime startDateTime = lotDto.getLotDateTime().getStartDateTime();
            LocalDateTime endDateTime = lotDto.getLotDateTime().getEndDateTime();

            errors.addAll(dateTimeUtils.checkThatDateTimeIsFuture(startDateTime,
                                                                  LOT_INVALID_START_TIME_MSG));
            errors.addAll(dateTimeUtils.checkThatDateTimeIsFuture(endDateTime,
                                                                  LOT_INVALID_END_TIME_MSG));
            errors.addAll(dateTimeUtils.checkThatStartDateTimeAndEndDateTimeHasCorrectOrder(
                startDateTime, endDateTime));
        }
        if (!errors.isEmpty()) {
            throw new LotCannotBeCreatedOrChangedException(errors);
        }
    }

    private Set<String> validateCommonAnnotations(LotDto lotDto) {
        return validatorFactory.getValidator()
                               .validate(lotDto)
                               .stream()
                               .map(ConstraintViolation::getMessage)
                               .collect(Collectors.toSet());
    }
}
