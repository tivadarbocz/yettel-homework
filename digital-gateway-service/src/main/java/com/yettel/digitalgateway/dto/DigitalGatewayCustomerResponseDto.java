package com.yettel.digitalgateway.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Schema(
    name = "DigitalGatewayCustomerResponse",
    description = "Base response for customer representations returned by the Digital Gateway",
    oneOf = {
        MobileCustomerResponseDto.class,
        ConsoleCustomerResponseDto.class,
        WebCustomerResponseDto.class
    }
)
public abstract class DigitalGatewayCustomerResponseDto {

    @Schema(example = "123", description = "Customer identifier")
    private String id;

    @Schema(example = "John Doe", description = "Customer name")
    private String name;

}