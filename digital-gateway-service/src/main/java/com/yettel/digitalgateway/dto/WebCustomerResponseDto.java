package com.yettel.digitalgateway.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Schema(
    name = "WebCustomerResponse",
    description = "Full customer response for web clients"
)
public class WebCustomerResponseDto extends DigitalGatewayCustomerResponseDto {

    @Schema(example = "Premium customer", description = "Customer description")
    private String description;

    @Schema(example = "Customer", description = "Type of the customer")
    private String type;

    @Schema(example = "Happy Travellers", description = "Name of the engaged party")
    private String engagedPartyName;

}