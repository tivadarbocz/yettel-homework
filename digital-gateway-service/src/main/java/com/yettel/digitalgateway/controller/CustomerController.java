package com.yettel.digitalgateway.controller;

import com.yettel.digitalgateway.client.CustomerClient;
import com.yettel.digitalgateway.client.customer.model.CustomerResponseDto;
import com.yettel.digitalgateway.dto.CustomerResponseFactory;
import com.yettel.digitalgateway.dto.DigitalGatewayCustomerResponseDto;
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
    public DigitalGatewayCustomerResponseDto getCustomer(
        @PathVariable("id") String id,
        @RequestHeader(value = "X-Platform", defaultValue = "WEB") String platform
    ) {
        CustomerResponseDto customer = customerClient.getCustomer(id);
        return factory.create(customer, platform);
    }
}