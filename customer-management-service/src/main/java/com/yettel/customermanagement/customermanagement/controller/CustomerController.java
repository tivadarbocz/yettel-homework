package com.yettel.customermanagement.customermanagement.controller;

import com.yettel.customermanagement.customermanagement.dto.CustomerRequestDto;
import com.yettel.customermanagement.customermanagement.dto.CustomerResponseDto;
import com.yettel.customermanagement.customermanagement.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
@Tag(name = "Customer", description = "Customer management APIs")
public class CustomerController {

    private final CustomerService service;

    @Operation(
            summary = "Creates a Customer",
            description = "This operation creates a Customer entity"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Customer created"),
        @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    @PostMapping
    public ResponseEntity<CustomerResponseDto> save(
            @Valid @RequestBody CustomerRequestDto request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.save(request));
    }

    @Operation(
            summary = "Retrieves a Customer by ID",
            description = "This operation retrieves a Customer entity. Attribute selection enabled for all first level attributes."
    )
    @GetMapping("/{id}")
    public CustomerResponseDto get(
        @Parameter(description = "Customer ID", example = "1")
        @PathVariable("id") String id) {

        return service.get(id);
    }

    @Operation(
            summary = "Updates partially a Customer",
            description = "This operation updates partially a Customer entity."
    )
    @PatchMapping("/{id}")
    public CustomerResponseDto update(
        @Parameter(description = "Customer ID", example = "1")
        @PathVariable("id") String id,
        @RequestBody CustomerRequestDto request) {

        return service.update(id, request);
    }

    @Operation(
            summary = "Deletes a Customer",
            description = "This operation deletes a Customer entity."
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
        @PathVariable("id") String id) {

        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}