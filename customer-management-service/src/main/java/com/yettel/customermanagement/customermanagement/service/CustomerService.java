package com.yettel.customermanagement.customermanagement.service;

import com.yettel.customermanagement.customermanagement.dto.CustomerRequestDto;
import com.yettel.customermanagement.customermanagement.dto.CustomerResponseDto;
import com.yettel.customermanagement.customermanagement.exception.NotFoundException;
import com.yettel.customermanagement.customermanagement.mapper.CustomerMapper;
import com.yettel.customermanagement.customermanagement.model.Customer;
import com.yettel.customermanagement.customermanagement.model.EngagedParty;
import com.yettel.customermanagement.customermanagement.repository.CustomerRepository;
import com.yettel.customermanagement.customermanagement.repository.EngagedPartyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final EngagedPartyRepository engagedPartyRepository;
    private final CustomerMapper mapper;

    public CustomerResponseDto save(CustomerRequestDto request) {

        EngagedParty party = engagedPartyRepository
                .getReferenceById(request.getEngagedPartyId());

        Customer customer = mapper.toEntity(request);
        customer.setEngagedParty(party);

        Customer saved = customerRepository.save(customer);

        return mapper.toDto(saved);
    }

    public CustomerResponseDto get(String id) {

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Customer.class.getName(), id));

        return mapper.toDto(customer);
    }

    public CustomerResponseDto update(String id, CustomerRequestDto request) {

        Customer existing = customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Customer.class.getName(), id));

        if (request.getTypeValue() != null) {
            existing.setTypeValue(request.getTypeValue());
        }

        if (request.getName() != null) {
            existing.setName(request.getName());
        }

        if (request.getDescription() != null) {
            existing.setDescription(request.getDescription());
        }

        if (request.getEngagedPartyId() != null) {
            EngagedParty party = engagedPartyRepository
                    .getReferenceById(request.getEngagedPartyId());

            existing.setEngagedParty(party);
        }

        Customer updated = customerRepository.save(existing);

        return mapper.toDto(updated);
    }

    public void delete(String id) {
        customerRepository.deleteById(id);
    }
}