
package com.legacycrm.repo;

import com.legacycrm.model.Opportunity;
import java.util.List;
import java.util.Optional;

/**
 * OpportunityRepository defines the data access contract for opportunity persistence.
 * 
 * Business Purpose:
 * - Provides abstraction layer for opportunity data storage and retrieval
 * - Supports different storage implementations (in-memory, database, etc.)
 * - Enables testing with mock implementations
 * - Follows Repository pattern for clean architecture separation
 * 
 * Data Access Patterns:
 * - CRUD operations for opportunity management
 * - Supports opportunity lookup for sales pipeline management
 * - Enables opportunity listing for sales forecasting and reporting
 * - Provides opportunity deletion for data management (use carefully due to referential integrity)
 * 
 * Implementation Notes:
 * - Implementations should handle data consistency and integrity
 * - Consider adding query methods for opportunity filtering by stage, amount, etc.
 * - May include caching strategies for performance optimization
 * - Should maintain referential integrity with source leads
 */
public interface OpportunityRepository {
    Opportunity save(Opportunity opportunity);
    Optional<Opportunity> findById(String id);
    List<Opportunity> findAll();
    void deleteById(String id);
}
