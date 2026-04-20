package com.yettel.customermanagement.customermanagement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponseDto {

    @Schema(example = "8416647f-7c4b-4487-85e2-30705ebee482")
    private String id;

    @Schema(example = "Customer")
    @JsonProperty("type")
    private String typeValue;

    @Schema(example = "Moon Football Club")
    private String name;

    @Schema(example = "Optional description")
    private String description;

    @Schema(example = "Engaged Party")
    private EngagedPartyDto engagedParty;
}