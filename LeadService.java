
package com.legacycrm.service;

import com.legacycrm.model.Lead;
import com.legacycrm.repo.LeadRepository;
import java.util.Optional;

/**
 * Service class providing business logic operations for Lead management.
 * 
 * <p>This service acts as the primary interface between the presentation layer
 * and the data access layer for Lead-related operations. It encapsulates
 * business rules, validation logic, and coordinates with the repository layer
 * for data persistence.</p>
 * 
 * <h3>Responsibilities:</h3>
 * <ul>
 *   <li><b>Lead Creation:</b> Creates new leads with proper validation</li>
 *   <li><b>Lead Retrieval:</b> Provides access to existing leads</li>
 *   <li><b>Business Logic:</b> Enforces lead-specific business rules</li>
 *   <li><b>Data Coordination:</b> Coordinates with repository for persistence</li>
 * </ul>
 * 
 * <h3>Design Principles:</h3>
 * <ul>
 *   <li><b>Single Responsibility:</b> Focuses solely on lead business operations</li>
 *   <li><b>Dependency Injection:</b> Uses constructor injection for repository dependency</li>
 *   <li><b>Immutable Dependencies:</b> Repository dependency is final and immutable</li>
 *   <li><b>Stateless:</b> Contains no instance state beyond dependencies</li>
 * </ul>
 * 
 * <h3>Usage Example:</h3>
 * <pre>{@code
 * LeadRepository repository = new InMemoryLeadRepository();
 * LeadService service = new LeadService(repository);
 * 
 * // Create a new lead
 * Lead lead = service.createLead("John", "Doe", "Acme Corp", 
 *                                "john.doe@acme.com", "+1-555-0100");
 * 
 * // Retrieve the lead
 * Optional<Lead> retrieved = service.getLead(lead.getId());
 * }</pre>
 * 
 * @author CRM Development Team
 * @version 1.0
 * @since 1.0
 * @see Lead
 * @see LeadRepository
 * @see ConversionService
 */
public class LeadService {
    /** Repository for lead data persistence operations */
    private final LeadRepository leadRepository;

    /**
     * Constructs a new LeadService with the specified repository.
     * 
     * <p>Uses dependency injection pattern to provide the service with
     * its required data access dependency. This enables testability
     * and flexibility in choosing different repository implementations.</p>
     * 
     * @param leadRepository the repository for lead data operations (must not be null)
     * @throws IllegalArgumentException if leadRepository is null
     */
    public LeadService(LeadRepository leadRepository) {
        this.leadRepository = leadRepository;
    }

    /**
     * Creates a new lead with the provided contact information.
     * 
     * <p>This method handles the complete lead creation process:</p>
     * <ol>
     *   <li>Creates a new Lead entity with provided details</li>
     *   <li>Lead is initialized with "New" status and current timestamp</li>
     *   <li>Persists the lead through the repository</li>
     *   <li>Returns the persisted lead instance</li>
     * </ol>
     * 
     * <h3>Business Rules Applied:</h3>
     * <ul>
     *   <li>Each lead gets a unique UUID identifier</li>
     *   <li>Default status is set to "New"</li>
     *   <li>Creation and update timestamps are set to current time</li>
     *   <li>Conversion flag is initialized to false</li>
     * </ul>
     * 
     * @param firstName the first name of the lead contact (required)
     * @param lastName the last name of the lead contact (required)
     * @param company the company name where the lead works (required)
     * @param email the email address of the lead contact (required)
     * @param phone the phone number of the lead contact (required)
     * 
     * @return the newly created and persisted lead
     * @throws IllegalArgumentException if any parameter is null or empty
     * @throws RuntimeException if persistence operation fails
     */
    public Lead createLead(String firstName, String lastName, String company, String email, String phone) {
        Lead lead = new Lead(firstName, lastName, company, email, phone);
        return leadRepository.save(lead);
    }

    /**
     * Retrieves a lead by its unique identifier.
     * 
     * <p>This method provides a simple lookup mechanism for retrieving
     * leads by their ID. It delegates directly to the repository layer.</p>
     * 
     * @param id the unique identifier of the lead to retrieve (must not be null)
     * @return an Optional containing the lead if found, or empty if not found
     * @throws IllegalArgumentException if id is null
     * @throws RuntimeException if retrieval operation fails
     */
    public Optional<Lead> getLead(String id) {
        return leadRepository.findById(id);
    }
}
