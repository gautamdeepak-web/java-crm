
package com.legacycrm.repo;

import com.legacycrm.model.Lead;
import java.util.*;

public class InMemoryLeadRepository implements LeadRepository {
    private final Map<String, Lead> store = new HashMap<>();

    @Override
    public Lead save(Lead lead) {
        store.put(lead.getId(), lead);
        return lead;
    }

    @Override
    public Optional<Lead> findById(String id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Lead> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void deleteById(String id) {
        store.remove(id);
    }
}
