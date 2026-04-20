package com.yettel.customermanagement.customermanagement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EngagedPartyDto {

    @NotBlank
    @Schema(example = "53af0a1c-088b-427c-9305-a72f41374415")
    private String id;

    @NotBlank
    @JsonProperty("type")
    @Schema(example = "PartyRef")
    private String typeValue;

    @Schema(example = "Happy Travellers")
    private String name;

}