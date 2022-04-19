package com.auction.integration.controller.lot;

import static com.auction.testinstance.lot.TestInstanceLot.getLot;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.testcontainers.shaded.org.awaitility.Awaitility.await;

import com.auction.dto.lot.LotDto;
import com.auction.entity.lot.Lot;
import com.auction.integration.controller.testcontainer.PostgreSqlContainer;
import com.auction.mapper.lot.LotMapper;
import com.auction.repository.lot.LotRepository;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
public class LotControllerIT {

    private static final String API_LOTS = "/api/v1/lots/";

    @Container
    public static PostgreSqlContainer postgreSQLContainer = PostgreSqlContainer.getInstance();

    private final LocalDateTime startDateTime = LocalDateTime.now()
                                                             .plus(Duration.ofHours(1))
                                                             .truncatedTo(ChronoUnit.SECONDS);

    private final LocalDateTime endDateTime = startDateTime.plus(Duration.ofHours(1));

    private final ObjectMapper mapper = new ObjectMapper()
        .configure(MapperFeature.USE_ANNOTATIONS, false)
        .findAndRegisterModules();

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private LotRepository lotRepository;

    @AfterEach
    public void cleanDatabase() {
        lotRepository.deleteAll();
    }

    @Test
    void createLot_LotIsCreated_ReturnLot() throws Exception {
        Lot lot = getLot(startDateTime, endDateTime);
        LotDto request = LotMapper.instance.convert(lot);

        MvcResult result = mockMvc.perform(post(API_LOTS)
                                               .contentType(MediaType.APPLICATION_JSON)
                                               .content(mapper.writeValueAsString(request)))
                                  .andExpect(status().isOk())
                                  .andReturn();

        LotDto expectedResult = LotMapper.instance.convert(lot);

        LotDto actualResult = mapper.readValue(result.getResponse().getContentAsString(),
                                               LotDto.class);

        await().untilAsserted(
            () -> assertThat(actualResult).usingRecursiveComparison()
                                          .ignoringFields("id", "images.id", "images.lotId")
                                          .isEqualTo(expectedResult)
        );
        assertEquals(1, lotRepository.findAll().size());
    }
}
