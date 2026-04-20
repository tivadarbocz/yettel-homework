package com.yettel.digitalgateway.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponseDto {

    private int status;
    private String error;
    private String message;
    private String traceId;
    private String path;
    private Instant timestamp;
    private String resource;
    private String resourceId;

}