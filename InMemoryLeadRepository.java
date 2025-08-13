
package com.legacycrm.repo;

import com.legacycrm.model.Lead;
import java.util.*;

/**
 * In-memory implementation of the LeadRepository interface.
 * 
 * <p>This implementation uses a HashMap to store Lead entities in memory,
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
 * that provide persistence, scalability, and ACID transaction support.</p>
 * 
 * @author CRM Development Team
 * @version 1.0
 * @since 1.0
 * @see LeadRepository
 * @see Lead
 */
public class InMemoryLeadRepository implements LeadRepository {
    /** Internal storage map using lead ID as key for fast lookups */
    private final Map<String, Lead> store = new HashMap<>();

    /**
     * {@inheritDoc}
     * 
     * <p>In this implementation, the lead is stored in the internal HashMap
     * using its ID as the key. The same lead instance is returned.</p>
     */
    @Override
    public Lead save(Lead lead) {
        store.put(lead.getId(), lead);
        return lead;
    }

    /**
     * {@inheritDoc}
     * 
     * <p>In this implementation, performs a HashMap lookup by ID.</p>
     */
    @Override
    public Optional<Lead> findById(String id) {
        return Optional.ofNullable(store.get(id));
    }

    /**
     * {@inheritDoc}
     * 
     * <p>In this implementation, returns a new ArrayList containing all stored leads.
     * The returned list is a defensive copy and can be safely modified.</p>
     */
    @Override
    public List<Lead> findAll() {
        return new ArrayList<>(store.values());
    }

    /**
     * {@inheritDoc}
     * 
     * <p>In this implementation, removes the lead from the internal HashMap.
     * No error occurs if the lead doesn't exist.</p>
     */
    @Override
    public void deleteById(String id) {
        store.remove(id);
    }
}
