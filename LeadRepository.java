
package com.legacycrm.repo;

import com.legacycrm.model.Lead;
import java.util.List;
import java.util.Optional;

/**
 * Repository interface for Lead data access operations.
 * 
 * <p>This interface defines the contract for persisting and retrieving Lead entities
 * from the underlying data store. It follows the Repository pattern to abstract
 * data access logic from business logic, enabling different storage implementations
 * (in-memory, database, file system, etc.) without changing business code.</p>
 * 
 * <h3>Design Principles:</h3>
 * <ul>
 *   <li><b>Abstraction:</b> Hides implementation details of data storage</li>
 *   <li><b>Testability:</b> Enables easy mocking for unit tests</li>
 *   <li><b>Flexibility:</b> Allows switching between storage mechanisms</li>
 *   <li><b>Separation of Concerns:</b> Isolates data access from business logic</li>
 * </ul>
 * 
 * <h3>Implementation Notes:</h3>
 * <ul>
 *   <li>Implementations should handle data validation and constraints</li>
 *   <li>IDs are managed by the Lead entity (UUID generation)</li>
 *   <li>Optional return types handle cases where entities are not found</li>
 *   <li>Collections returned should be defensive copies when appropriate</li>
 * </ul>
 * 
 * @author CRM Development Team
 * @version 1.0
 * @since 1.0
 * @see Lead
 * @see InMemoryLeadRepository
 */
public interface LeadRepository {
    /**
     * Persists a lead entity to the data store.
     * 
     * <p>If the lead already exists (same ID), this operation will update the existing
     * record. If the lead is new, it will be inserted into the data store.</p>
     * 
     * @param lead the lead entity to persist (must not be null)
     * @return the persisted lead entity (may include generated fields)
     * @throws IllegalArgumentException if lead is null
     * @throws RuntimeException if persistence operation fails
     */
    Lead save(Lead lead);
    
    /**
     * Retrieves a lead by its unique identifier.
     * 
     * @param id the unique identifier of the lead to retrieve (must not be null)
     * @return an Optional containing the lead if found, or empty if not found
     * @throws IllegalArgumentException if id is null
     */
    Optional<Lead> findById(String id);
    
    /**
     * Retrieves all leads from the data store.
     * 
     * <p>This operation may be expensive for large datasets. Consider implementing
     * pagination or filtering in production systems.</p>
     * 
     * @return a list of all leads (empty list if no leads exist)
     * @throws RuntimeException if retrieval operation fails
     */
    List<Lead> findAll();
    
    /**
     * Removes a lead from the data store by its unique identifier.
     * 
     * <p>This operation is idempotent - calling it multiple times with the same ID
     * will not cause errors, even if the lead has already been deleted.</p>
     * 
     * @param id the unique identifier of the lead to delete (must not be null)
     * @throws IllegalArgumentException if id is null
     * @throws RuntimeException if deletion operation fails
     */
    void deleteById(String id);
}
