package com.yettel.digitalgateway.client;

import com.yettel.digitalgateway.client.customer.model.CustomerRequestDto;
import com.yettel.digitalgateway.client.customer.model.CustomerResponseDto;

public interface CustomerClient {

    CustomerResponseDto getCustomer(String id);

    CustomerResponseDto createCustomer(CustomerRequestDto request);

    CustomerResponseDto updateCustomer(String id, CustomerRequestDto request);

    void deleteCustomer(String id);
}