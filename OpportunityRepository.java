
package com.legacycrm.repo;

import com.legacycrm.model.Opportunity;
import java.util.List;
import java.util.Optional;

/**
 * Repository interface for Opportunity data access operations.
 * 
 * <p>This interface defines the contract for persisting and retrieving Opportunity entities
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
 *   <li>IDs are managed by the Opportunity entity (UUID generation)</li>
 *   <li>Optional return types handle cases where entities are not found</li>
 *   <li>Collections returned should be defensive copies when appropriate</li>
 *   <li>Consider implementing queries by stage, amount range, or source lead</li>
 * </ul>
 * 
 * @author CRM Development Team
 * @version 1.0
 * @since 1.0
 * @see Opportunity
 * @see InMemoryOpportunityRepository
 */
public interface OpportunityRepository {
    /**
     * Persists an opportunity entity to the data store.
     * 
     * <p>If the opportunity already exists (same ID), this operation will update the existing
     * record. If the opportunity is new, it will be inserted into the data store.</p>
     * 
     * @param opportunity the opportunity entity to persist (must not be null)
     * @return the persisted opportunity entity (may include generated fields)
     * @throws IllegalArgumentException if opportunity is null
     * @throws RuntimeException if persistence operation fails
     */
    Opportunity save(Opportunity opportunity);
    
    /**
     * Retrieves an opportunity by its unique identifier.
     * 
     * @param id the unique identifier of the opportunity to retrieve (must not be null)
     * @return an Optional containing the opportunity if found, or empty if not found
     * @throws IllegalArgumentException if id is null
     */
    Optional<Opportunity> findById(String id);
    
    /**
     * Retrieves all opportunities from the data store.
     * 
     * <p>This operation may be expensive for large datasets. Consider implementing
     * pagination or filtering in production systems. Typical filters might include
     * stage, amount range, account name, or source lead.</p>
     * 
     * @return a list of all opportunities (empty list if no opportunities exist)
     * @throws RuntimeException if retrieval operation fails
     */
    List<Opportunity> findAll();
    
    /**
     * Removes an opportunity from the data store by its unique identifier.
     * 
     * <p>This operation is idempotent - calling it multiple times with the same ID
     * will not cause errors, even if the opportunity has already been deleted.</p>
     * 
     * @param id the unique identifier of the opportunity to delete (must not be null)
     * @throws IllegalArgumentException if id is null
     * @throws RuntimeException if deletion operation fails
     */
    void deleteById(String id);
}
