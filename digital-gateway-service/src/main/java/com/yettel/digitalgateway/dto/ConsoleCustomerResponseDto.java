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
    name = "ConsoleCustomerResponse",
    description = "Medium-detail customer response for console/backoffice clients"
)
public class ConsoleCustomerResponseDto extends DigitalGatewayCustomerResponseDto {

    @Schema(example = "Customer", description = "Type of the customer")
    private String type;

}