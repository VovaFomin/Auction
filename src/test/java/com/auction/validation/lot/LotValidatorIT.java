package com.auction.validation.lot;

import static com.auction.testinstance.TestInstance.getLotDto;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.auction.dto.LotDto;
import com.auction.exception.LotCannotBeCreatedOrChangedException;
import com.auction.integration.controller.testcontainer.PostgreSqlContainer;
import com.auction.repository.lot.LotRepository;
import com.auction.repository.user.UserRepository;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.transaction.TransactionAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@EnableAutoConfiguration(exclude = {
    LiquibaseAutoConfiguration.class,
    HibernateJpaAutoConfiguration.class,
    TransactionAutoConfiguration.class,
})
@Testcontainers
class LotValidatorIT {
    private static final String LOT_INVALID_DATE_ORDER_MSG =
        "Invalid End Time. The End Time should be "
        + "later than the Start Time.";
    private static final String LOT_INVALID_START_TIME_MSG =
        "Invalid Start Time. The Start Time should "
        + "be not earlier than current time.";
    private static final String LOT_INVALID_END_TIME_MSG =
        "Invalid End Time. The End Time should be not "
        + "earlier than current time.";

    @Autowired
    private LotValidator lotValidator;

    @MockBean
    private LotRepository lotRepository;

    @MockBean
    private UserRepository userRepository;

    @Container
    public static PostgreSqlContainer postgreSQLContainer = PostgreSqlContainer.getInstance();


    @Test
    void validateCreate_startDateTimeIsBeforeCurrentTime_throwException() {
        LocalDateTime startDateTime = LocalDateTime.now().minus(Duration.ofHours(2));
        LocalDateTime endDateTime = LocalDateTime.now().plus(Duration.ofHours(2));
        LotDto lotDto = getLotDto(startDateTime, endDateTime);

        LotCannotBeCreatedOrChangedException exception =
            assertThrows(LotCannotBeCreatedOrChangedException.class,
                         () -> lotValidator.validateCreate(lotDto));

        assertEquals(Set.of(LOT_INVALID_START_TIME_MSG), exception.getMessages());
    }

    @Test
    void validateCreate_endDateTimeIsBeforeCurrentTime_throwException() {
        LocalDateTime endDateTime = LocalDateTime.now().minus(Duration.ofHours(3));
        LotDto lotDto = getLotDto(null, endDateTime);

        LotCannotBeCreatedOrChangedException exception =
            assertThrows(LotCannotBeCreatedOrChangedException.class,
                         () -> lotValidator.validateCreate(lotDto));

        assertEquals(Set.of(LOT_INVALID_END_TIME_MSG), exception.getMessages());
    }

    @Test
    void validateCreate_OrderOfStartDateAndEndDateIsChanged_throwException() {
        LocalDateTime startDateTime = LocalDateTime.now().plus(Duration.ofHours(3));
        LocalDateTime endDateTime = LocalDateTime.now().plus(Duration.ofHours(1));
        LotDto lotDto = getLotDto(startDateTime, endDateTime);

        LotCannotBeCreatedOrChangedException exception =
            assertThrows(LotCannotBeCreatedOrChangedException.class,
                         () -> lotValidator.validateCreate(lotDto));

        assertEquals(Set.of(LOT_INVALID_DATE_ORDER_MSG), exception.getMessages());
    }

    @Test
    void validateCreate_StartDateTimeIsNull_ExceptionDoesNotThrow() {
        LocalDateTime endDateTime = LocalDateTime.now().plus(Duration.ofHours(1));
        LotDto lotDto = getLotDto(null, endDateTime);

        assertDoesNotThrow(() -> lotValidator.validateCreate(lotDto));
    }

    @Test
    void validateCreate_EndDateTimeIsNull_ExceptionDoesNotThrow() {
        LocalDateTime startDateTime = LocalDateTime.now().plus(Duration.ofHours(1));
        LotDto lotDto = getLotDto(startDateTime, null);

        assertDoesNotThrow(() -> lotValidator.validateCreate(lotDto));
    }
}
