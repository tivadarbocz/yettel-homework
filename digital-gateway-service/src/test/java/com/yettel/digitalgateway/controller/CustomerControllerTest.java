package com.yettel.digitalgateway.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yettel.digitalgateway.client.CustomerClient;
import com.yettel.digitalgateway.client.customer.model.CustomerResponseDto;
import com.yettel.digitalgateway.dto.ConsoleCustomerResponseDto;
import com.yettel.digitalgateway.dto.CustomerResponseFactory;
import com.yettel.digitalgateway.dto.DigitalGatewayCustomerResponseDto;
import com.yettel.digitalgateway.dto.MobileCustomerResponseDto;
import com.yettel.digitalgateway.dto.WebCustomerResponseDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;
import java.util.stream.Stream;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CustomerClient customerClient;

    @MockitoBean
    private CustomerResponseFactory factory;

    @Autowired
    private ObjectMapper objectMapper;

    static Stream<TestScenario> platformScenarios() {
        return Stream.of(
            new TestScenario(
                "WEB",
                WebCustomerResponseDto.builder()
                    .id("1")
                    .name("John Web")
                    .description("web desc")
                    .type("WEB_TYPE")
                    .engagedPartyName("WEB_PARTY")
                    .build()
            ),
            new TestScenario(
                "MOBILE",
                MobileCustomerResponseDto.builder()
                    .id("1")
                    .name("John Mobile")
                    .build()
            ),
            new TestScenario(
                "CONSOLE",
                ConsoleCustomerResponseDto.builder()
                    .id("1")
                    .name("John Console")
                    .type("CONSOLE_TYPE")
                    .build()
            )
        );
    }

    @ParameterizedTest
    @MethodSource("platformScenarios")
    @DisplayName("Given platform header, when getCustomer is called, then return platform-specific response")
    void shouldReturnPlatformSpecificCustomerResponse(TestScenario scenario) throws Exception {

        // given
        String customerId = UUID.randomUUID().toString();

        CustomerResponseDto externalCustomer = new CustomerResponseDto();
        externalCustomer.setId(customerId);
        externalCustomer.setName("John");

        Mockito.when(customerClient.getCustomer(customerId)).thenReturn(externalCustomer);
        Mockito.when(factory.create(externalCustomer, scenario.platform))
            .thenReturn(scenario.expectedResponse);

        // when
        mockMvc.perform(get("/api/v1/customers/{id}", customerId)
                .header("X-Platform", scenario.platform)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().json(objectMapper.writeValueAsString(scenario.expectedResponse)));

        // then
        Mockito.verify(customerClient).getCustomer(customerId);
        Mockito.verify(factory).create(externalCustomer, scenario.platform);
    }

    static class TestScenario {
        String platform;
        DigitalGatewayCustomerResponseDto expectedResponse;

        TestScenario(String platform, DigitalGatewayCustomerResponseDto expectedResponse) {
            this.platform = platform;
            this.expectedResponse = expectedResponse;
        }
    }

}