
package com.legacycrm.repo;

import com.legacycrm.model.Opportunity;
import java.util.*;

public class InMemoryOpportunityRepository implements OpportunityRepository {
    private final Map<String, Opportunity> store = new HashMap<>();

    @Override
    public Opportunity save(Opportunity opportunity) {
        store.put(opportunity.getId(), opportunity);
        return opportunity;
    }

    @Override
    public Optional<Opportunity> findById(String id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Opportunity> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void deleteById(String id) {
        store.remove(id);
    }
}
