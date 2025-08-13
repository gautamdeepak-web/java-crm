
package com.legacycrm.repo;

import com.legacycrm.model.Lead;
import java.util.List;
import java.util.Optional;

/**
 * LeadRepository defines the data access contract for lead persistence.
 * 
 * Business Purpose:
 * - Provides abstraction layer for lead data storage and retrieval
 * - Supports different storage implementations (in-memory, database, etc.)
 * - Enables testing with mock implementations
 * - Follows Repository pattern for clean architecture separation
 * 
 * Data Access Patterns:
 * - CRUD operations for lead management
 * - Supports lead lookup during conversion process
 * - Enables lead listing for sales team workflows
 * - Provides lead deletion for data management (use carefully due to referential integrity)
 * 
 * Implementation Notes:
 * - Implementations should handle data consistency and integrity
 * - Consider adding query methods for lead filtering and searching
 * - May include caching strategies for performance optimization
 * - Should validate business rules at persistence layer
 */
public interface LeadRepository {
    Lead save(Lead lead);
    Optional<Lead> findById(String id);
    List<Lead> findAll();
    void deleteById(String id);
}
