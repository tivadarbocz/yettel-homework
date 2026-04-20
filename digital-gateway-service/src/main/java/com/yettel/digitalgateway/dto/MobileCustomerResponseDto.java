package com.yettel.digitalgateway.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(
    name = "MobileCustomerResponse",
    description = "Lightweight customer response optimized for mobile clients"
)
public class MobileCustomerResponseDto extends DigitalGatewayCustomerResponseDto {

}