
package com.legacycrm.repo;

import com.legacycrm.model.Opportunity;
import java.util.List;
import java.util.Optional;

public interface OpportunityRepository {
    Opportunity save(Opportunity opportunity);
    Optional<Opportunity> findById(String id);
    List<Opportunity> findAll();
    void deleteById(String id);
}
