package com.yettel.digitalgateway.dto;

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
public class WebCustomerResponseDto extends DigitalGatewayCustomerResponseDto {

    private String description;
    private String type;
    private String engagedPartyName;

}