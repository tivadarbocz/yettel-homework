package com.yettel.customermanagement.customermanagement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequestDto {

    @NotBlank
    @Schema(example = "Customer")
    @JsonProperty("type")
    private String typeValue;

    @Schema(example = "Moon Football Club")
    @NotBlank
    private String name;

    @Schema(example = "Optional description")
    private String description;

    @Schema(example = "53af0a1c-088b-427c-9305-a72f41374415", description = "Engaged Party ID reference")
    @NotNull
    private String engagedPartyId;

}