package com.between.product_price;

import com.between.product_price.infrastructure.web.dto.PriceDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerPriceApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private static String REQ_JSON_PATH = "src/test/java/resources/json/";

    static List<Object[]> suitCaseData() {
        return Arrays.asList(
                new Object[]{LocalDateTime.of(2020, 6, 14, 10, 0), 35455L, 1L,
                        REQ_JSON_PATH.concat("test_case_1.json")},
                new Object[]{LocalDateTime.of(2020, 6, 14, 16, 0), 35455L, 1L,
                        REQ_JSON_PATH.concat("test_case_2.json")},
                new Object[]{LocalDateTime.of(2020, 6, 14, 21, 0), 35455L, 1L,
                        REQ_JSON_PATH.concat("test_case_3.json")},
                new Object[]{LocalDateTime.of(2020, 6, 15, 10, 0), 35455L, 1L,
                        REQ_JSON_PATH.concat("test_case_4.json")},
                new Object[]{LocalDateTime.of(2020, 6, 16, 21, 0), 35455L, 1L,
                        REQ_JSON_PATH.concat("test_case_5.json")}
        );
    }

    @ParameterizedTest
    @MethodSource("suitCaseData")
    public void testFetchPriceInfo_shouldBeReturnSuccess(LocalDateTime applicationDate, Long productId, Long brandId,
                                                         String expectedResponsePath) throws Exception {
        String jsonResponse = mockMvc.perform(MockMvcRequestBuilders.get("/product/prices")
                        .param("applicationDate", applicationDate.toString())
                        .param("productId", String.valueOf(productId))
                        .param("brandId", String.valueOf(brandId))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        PriceDTO actualResponse = objectMapper.readValue(jsonResponse, PriceDTO.class);

        String mockJsonContent = new String(Files.readAllBytes(Paths.get(expectedResponsePath)));
        PriceDTO expectedResponse = objectMapper.readValue(mockJsonContent, PriceDTO.class);

        Assertions.assertEquals(expectedResponse.getId(), actualResponse.getId());
        Assertions.assertEquals(expectedResponse.getBrandDTO().getBrandName(), actualResponse.getBrandDTO().getBrandName());
        Assertions.assertEquals(expectedResponse.getStartDate(), actualResponse.getStartDate());
        Assertions.assertEquals(expectedResponse.getEndDate(), actualResponse.getEndDate());
        Assertions.assertEquals(expectedResponse.getPriceList(), actualResponse.getPriceList());
        Assertions.assertEquals(expectedResponse.getProductId(), actualResponse.getProductId());
        Assertions.assertEquals(expectedResponse.getPriority(), actualResponse.getPriority());
        Assertions.assertEquals(expectedResponse.getPrice(), actualResponse.getPrice());
        Assertions.assertEquals(expectedResponse.getCurrency(), actualResponse.getCurrency());
    }
}
