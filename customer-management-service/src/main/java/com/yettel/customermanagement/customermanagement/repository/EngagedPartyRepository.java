package com.yettel.customermanagement.customermanagement.repository;

import com.yettel.customermanagement.customermanagement.model.EngagedParty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EngagedPartyRepository extends JpaRepository<EngagedParty, String> {

}
