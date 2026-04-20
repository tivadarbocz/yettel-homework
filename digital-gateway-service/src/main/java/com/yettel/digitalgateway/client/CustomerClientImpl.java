package com.yettel.digitalgateway.client;

import com.yettel.digitalgateway.client.customer.api.CustomerApi;
import com.yettel.digitalgateway.client.customer.invoker.ApiClient;
import com.yettel.digitalgateway.client.customer.model.CustomerRequestDto;
import com.yettel.digitalgateway.client.customer.model.CustomerResponseDto;

import com.yettel.digitalgateway.exception.NotFoundException;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Component
public class CustomerClientImpl implements CustomerClient {

    private final CustomerApi api;

    public CustomerClientImpl(@Value("${customer.service.url}") String baseUrl) {
        WebClient webClient = WebClient.builder()
                .filter((request, next) -> {
                    String traceId = MDC.get("traceId");

                    return next.exchange(
                            ClientRequest.from(request)
                                    .header("X-Trace-Id", traceId)
                                    .build()
                    );
                })
                .build();

        ApiClient client = new ApiClient(webClient);
        client.setBasePath(baseUrl);

        this.api = new CustomerApi(client);
    }

    @Override
    public CustomerResponseDto getCustomer(String id) {
        try {
            return api.get(id).block();

        } catch (WebClientResponseException.NotFound ex) {
            throw new NotFoundException("Customer", id);
        } catch (WebClientResponseException ex) {
            throw new RuntimeException("Downstream error: " + ex.getStatusCode(), ex);
        }
    }

    @Override
    public CustomerResponseDto createCustomer(CustomerRequestDto request) {
        try {
            return api.save(request).block();
        } catch (Exception e) {
            throw new RuntimeException("Failed to create customer", e);
        }
    }

    @Override
    public CustomerResponseDto updateCustomer(String id, CustomerRequestDto request) {
        try {
            return api.update(id, request).block();
        } catch (WebClientResponseException.NotFound ex) {
            throw new NotFoundException("Customer", id);
        } catch (WebClientResponseException ex) {
            throw new RuntimeException("Downstream error: " + ex.getStatusCode(), ex);
        }
    }

    @Override
    public void deleteCustomer(String id) {
        try {
            api.delete(id).block();
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete customer", e);
        }
    }

}