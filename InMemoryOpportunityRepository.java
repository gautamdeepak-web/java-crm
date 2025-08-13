
package com.legacycrm.repo;

import com.legacycrm.model.Opportunity;
import java.util.*;

/**
 * In-memory implementation of the OpportunityRepository interface.
 * 
 * <p>This implementation uses a HashMap to store Opportunity entities in memory,
 * providing fast access for development, testing, and demonstration purposes.
 * Data is not persisted beyond the application lifecycle.</p>
 * 
 * <h3>Characteristics:</h3>
 * <ul>
 *   <li><b>Performance:</b> Very fast read/write operations (O(1) average)</li>
 *   <li><b>Simplicity:</b> No external dependencies or configuration</li>
 *   <li><b>Volatility:</b> Data is lost when application stops</li>
 *   <li><b>Thread Safety:</b> Not thread-safe; use synchronization if needed</li>
 * </ul>
 * 
 * <h3>Use Cases:</h3>
 * <ul>
 *   <li>Development and testing environments</li>
 *   <li>Prototyping and demonstrations</li>
 *   <li>Unit testing with controlled data sets</li>
 *   <li>Small-scale applications with minimal data</li>
 * </ul>
 * 
 * <h3>Production Considerations:</h3>
 * <p>For production systems, consider replacing with database-backed implementations
 * that provide persistence, scalability, ACID transaction support, and advanced
 * querying capabilities (e.g., finding opportunities by stage, amount range).</p>
 * 
 * @author CRM Development Team
 * @version 1.0
 * @since 1.0
 * @see OpportunityRepository
 * @see Opportunity
 */
public class InMemoryOpportunityRepository implements OpportunityRepository {
    /** Internal storage map using opportunity ID as key for fast lookups */
    private final Map<String, Opportunity> store = new HashMap<>();

    /**
     * {@inheritDoc}
     * 
     * <p>In this implementation, the opportunity is stored in the internal HashMap
     * using its ID as the key. The same opportunity instance is returned.</p>
     */
    @Override
    public Opportunity save(Opportunity opportunity) {
        store.put(opportunity.getId(), opportunity);
        return opportunity;
    }

    /**
     * {@inheritDoc}
     * 
     * <p>In this implementation, performs a HashMap lookup by ID.</p>
     */
    @Override
    public Optional<Opportunity> findById(String id) {
        return Optional.ofNullable(store.get(id));
    }

    /**
     * {@inheritDoc}
     * 
     * <p>In this implementation, returns a new ArrayList containing all stored opportunities.
     * The returned list is a defensive copy and can be safely modified.</p>
     */
    @Override
    public List<Opportunity> findAll() {
        return new ArrayList<>(store.values());
    }

    /**
     * {@inheritDoc}
     * 
     * <p>In this implementation, removes the opportunity from the internal HashMap.
     * No error occurs if the opportunity doesn't exist.</p>
     */
    @Override
    public void deleteById(String id) {
        store.remove(id);
    }
}
