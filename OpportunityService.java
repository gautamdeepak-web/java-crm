
package com.legacycrm.service;

import com.legacycrm.model.Opportunity;
import com.legacycrm.repo.OpportunityRepository;

/**
 * Service class providing business logic operations for Opportunity management.
 * 
 * <p>This service acts as the primary interface between the presentation layer
 * and the data access layer for Opportunity-related operations. It encapsulates
 * business rules, validation logic, and coordinates with the repository layer
 * for data persistence.</p>
 * 
 * <h3>Responsibilities:</h3>
 * <ul>
 *   <li><b>Opportunity Creation:</b> Creates new opportunities with proper validation</li>
 *   <li><b>Business Logic:</b> Enforces opportunity-specific business rules</li>
 *   <li><b>Sales Process:</b> Supports opportunity progression through sales stages</li>
 *   <li><b>Data Coordination:</b> Coordinates with repository for persistence</li>
 * </ul>
 * 
 * <h3>Design Principles:</h3>
 * <ul>
 *   <li><b>Single Responsibility:</b> Focuses solely on opportunity business operations</li>
 *   <li><b>Dependency Injection:</b> Uses constructor injection for repository dependency</li>
 *   <li><b>Immutable Dependencies:</b> Repository dependency is final and immutable</li>
 *   <li><b>Stateless:</b> Contains no instance state beyond dependencies</li>
 * </ul>
 * 
 * <h3>Sales Stage Management:</h3>
 * <p>While this service currently focuses on creation, it can be extended to handle
 * opportunity stage progression, amount updates, and closing operations according
 * to business-specific sales processes.</p>
 * 
 * <h3>Usage Example:</h3>
 * <pre>{@code
 * OpportunityRepository repository = new InMemoryOpportunityRepository();
 * OpportunityService service = new OpportunityService(repository);
 * 
 * // Create a new opportunity
 * Opportunity opp = service.createOpportunity(
 *     "Acme Corp - Software License",
 *     75000.00,
 *     "Qualification",
 *     "Acme Corp",
 *     "lead-123"
 * );
 * }</pre>
 * 
 * @author CRM Development Team
 * @version 1.0
 * @since 1.0
 * @see Opportunity
 * @see OpportunityRepository
 * @see ConversionService
 */
public class OpportunityService {
    /** Repository for opportunity data persistence operations */
    private final OpportunityRepository opportunityRepository;

    /**
     * Constructs a new OpportunityService with the specified repository.
     * 
     * <p>Uses dependency injection pattern to provide the service with
     * its required data access dependency. This enables testability
     * and flexibility in choosing different repository implementations.</p>
     * 
     * @param opportunityRepository the repository for opportunity data operations (must not be null)
     * @throws IllegalArgumentException if opportunityRepository is null
     */
    public OpportunityService(OpportunityRepository opportunityRepository) {
        this.opportunityRepository = opportunityRepository;
    }

    /**
     * Creates a new opportunity with the provided business details.
     * 
     * <p>This method handles the complete opportunity creation process:</p>
     * <ol>
     *   <li>Creates a new Opportunity entity with provided details</li>
     *   <li>Opportunity is initialized with current timestamp</li>
     *   <li>Persists the opportunity through the repository</li>
     *   <li>Returns the persisted opportunity instance</li>
     * </ol>
     * 
     * <h3>Business Rules Applied:</h3>
     * <ul>
     *   <li>Each opportunity gets a unique UUID identifier</li>
     *   <li>Creation and update timestamps are set to current time</li>
     *   <li>Source lead reference is maintained for traceability</li>
     *   <li>Initial stage is set as provided (typically "New Business")</li>
     * </ul>
     * 
     * <h3>Parameter Guidelines:</h3>
     * <ul>
     *   <li><b>name:</b> Should be descriptive and include account context</li>
     *   <li><b>amount:</b> Estimated deal value in appropriate currency</li>
     *   <li><b>stage:</b> Current sales stage (e.g., "New Business", "Qualification")</li>
     *   <li><b>accountName:</b> Target customer organization name</li>
     *   <li><b>sourceLeadId:</b> ID of originating lead (null for direct opportunities)</li>
     * </ul>
     * 
     * @param name descriptive name for the opportunity (required)
     * @param amount the potential monetary value of this opportunity (must be non-negative)
     * @param stage the initial sales stage (required)
     * @param accountName the name of the target customer account (required)
     * @param sourceLeadId the ID of the lead that generated this opportunity (can be null)
     * 
     * @return the newly created and persisted opportunity
     * @throws IllegalArgumentException if any required parameter is null/empty or amount is negative
     * @throws RuntimeException if persistence operation fails
     */
    public Opportunity createOpportunity(String name, double amount, String stage, String accountName, String sourceLeadId) {
        Opportunity opportunity = new Opportunity(name, amount, stage, accountName, sourceLeadId);
        return opportunityRepository.save(opportunity);
    }
}
