package com.yettel.digitalgateway.controller;

import com.yettel.digitalgateway.client.CustomerClient;
import com.yettel.digitalgateway.client.customer.model.CustomerResponseDto;
import com.yettel.digitalgateway.dto.ConsoleCustomerResponseDto;
import com.yettel.digitalgateway.dto.CustomerResponseFactory;
import com.yettel.digitalgateway.dto.DigitalGatewayCustomerResponseDto;
import com.yettel.digitalgateway.dto.MobileCustomerResponseDto;
import com.yettel.digitalgateway.dto.WebCustomerResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerClient customerClient;
    private final CustomerResponseFactory factory;


    @GetMapping("/{id}")
    @Operation(
        summary = "Get customer by id",
        description = "Returns a platform-specific customer representation based on the X-Platform header"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Customer retrieved successfully",
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(
                oneOf = {
                    MobileCustomerResponseDto.class,
                    ConsoleCustomerResponseDto.class,
                    WebCustomerResponseDto.class
                }
            )
        )
    )
    public DigitalGatewayCustomerResponseDto getCustomer(
        @PathVariable("id") String id,
        @RequestHeader(value = "X-Platform", defaultValue = "WEB") String platform
    ) {
        CustomerResponseDto customer = customerClient.getCustomer(id);
        return factory.create(customer, platform);
    }
}