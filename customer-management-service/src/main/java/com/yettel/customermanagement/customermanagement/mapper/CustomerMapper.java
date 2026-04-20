package com.yettel.customermanagement.customermanagement.mapper;

import com.yettel.customermanagement.customermanagement.dto.CustomerRequestDto;
import com.yettel.customermanagement.customermanagement.dto.CustomerResponseDto;
import com.yettel.customermanagement.customermanagement.dto.EngagedPartyDto;
import com.yettel.customermanagement.customermanagement.model.Customer;
import com.yettel.customermanagement.customermanagement.model.EngagedParty;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", builder = @org.mapstruct.Builder(disableBuilder = true))
public interface CustomerMapper {

    CustomerResponseDto toDto(Customer entity);

    EngagedPartyDto toDto(EngagedParty entity);

    // we still map request → entity but WITHOUT relationships
    @Mapping(target = "engagedParty", ignore = true)
    Customer toEntity(CustomerRequestDto dto);
}