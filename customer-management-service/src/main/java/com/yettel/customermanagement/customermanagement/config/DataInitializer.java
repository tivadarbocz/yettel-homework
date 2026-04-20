package com.yettel.customermanagement.customermanagement.config;

import com.yettel.customermanagement.customermanagement.model.Customer;
import com.yettel.customermanagement.customermanagement.model.EngagedParty;
import com.yettel.customermanagement.customermanagement.repository.CustomerRepository;
import com.yettel.customermanagement.customermanagement.repository.EngagedPartyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final CustomerRepository customerRepository;
    private final EngagedPartyRepository engagedPartyRepository;

    @Override
    public void run(String... args) {
        EngagedParty partyHappyTravellers = new EngagedParty();
        partyHappyTravellers.setTypeValue("PartyRef");
        partyHappyTravellers.setName("Happy Travellers");
        engagedPartyRepository.save(partyHappyTravellers);

        EngagedParty partyDriversClub = new EngagedParty();
        partyDriversClub.setTypeValue("PartyRef");
        partyDriversClub.setName("Drivers Club");
        engagedPartyRepository.save(partyDriversClub);

        Customer customerMoonFootballClub = new Customer();
        customerMoonFootballClub.setTypeValue("Customer");
        customerMoonFootballClub.setName("Moon Football Club");
        customerMoonFootballClub.setDescription("Moon Football Club Description");
        customerMoonFootballClub.setEngagedParty(partyHappyTravellers);
        customerRepository.save(customerMoonFootballClub);

        Customer customerLocalGovernment = new Customer();
        customerLocalGovernment.setTypeValue("Customer");
        customerLocalGovernment.setName("Local Government");
        customerLocalGovernment.setDescription("Local Government Description");
        customerLocalGovernment.setEngagedParty(partyDriversClub);
        customerRepository.save(customerLocalGovernment);

        Customer customerLocalNursery = new Customer();
        customerLocalNursery.setTypeValue("Customer");
        customerLocalNursery.setName("Local Nursery");
        customerLocalNursery.setDescription("Local Nursery Description");
        customerLocalNursery.setEngagedParty(partyDriversClub);
        customerRepository.save(customerLocalNursery);
    }
}