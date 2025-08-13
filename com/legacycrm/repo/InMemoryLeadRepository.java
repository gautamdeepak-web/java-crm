
package com.legacycrm.repo;

import com.legacycrm.model.Lead;
import java.util.*;

/**
 * InMemoryLeadRepository provides an in-memory implementation of LeadRepository.
 * 
 * Business Purpose:
 * - Provides temporary storage for lead data during application runtime
 * - Suitable for development, testing, and demo environments
 * - Enables rapid prototyping without database setup requirements
 * - Serves as reference implementation for the LeadRepository interface
 * 
 * Implementation Details:
 * - Uses HashMap for O(1) lookup performance by lead ID
 * - Data is lost when application terminates (not persistent)
 * - Thread-safe for single-threaded applications (not concurrent-safe)
 * - All operations are synchronous and immediate
 * 
 * Usage Scenarios:
 * - Development and testing environments
 * - Demo applications and proof-of-concepts
 * - Unit testing with predictable data states
 * - Should be replaced with database implementation for production
 * 
 * Limitations:
 * - No data persistence across application restarts
 * - No concurrent access protection
 * - No query optimization or indexing
 * - Limited scalability for large datasets
 */
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
