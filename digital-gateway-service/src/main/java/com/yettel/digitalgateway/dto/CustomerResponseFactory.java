package com.yettel.digitalgateway.dto;

import com.yettel.digitalgateway.client.customer.model.CustomerResponseDto;
import org.springframework.stereotype.Component;

@Component
public class CustomerResponseFactory {

    public DigitalGatewayCustomerResponseDto create(CustomerResponseDto customer, String platformHeader) {
        Platform platform = Platform.from(platformHeader);

        return switch (platform) {
            case MOBILE -> MobileCustomerResponseDto.builder()
                    .id(customer.getId())
                    .name(customer.getName())
                    .build();

            case CONSOLE -> ConsoleCustomerResponseDto.builder()
                    .id(customer.getId())
                    .name(customer.getName())
                    .type(customer.getType())
                    .build();

            case WEB -> WebCustomerResponseDto.builder()
                    .id(customer.getId())
                    .name(customer.getName())
                    .type(customer.getType())
                    .description(customer.getDescription())
                    .engagedPartyName(
                            customer.getEngagedParty() != null
                                    ? customer.getEngagedParty().getName()
                                    : null
                    )
                    .build();
        };
    }
}