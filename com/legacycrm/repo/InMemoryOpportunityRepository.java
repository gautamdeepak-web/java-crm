
package com.legacycrm.repo;

import com.legacycrm.model.Opportunity;
import java.util.*;

/**
 * InMemoryOpportunityRepository provides an in-memory implementation of OpportunityRepository.
 * 
 * Business Purpose:
 * - Provides temporary storage for opportunity data during application runtime
 * - Suitable for development, testing, and demo environments
 * - Enables rapid prototyping without database setup requirements
 * - Serves as reference implementation for the OpportunityRepository interface
 * 
 * Implementation Details:
 * - Uses HashMap for O(1) lookup performance by opportunity ID
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
 * - No query optimization or indexing for sales pipeline queries
 * - Limited scalability for large opportunity datasets
 */
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
