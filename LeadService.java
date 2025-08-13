
package com.legacycrm.service;

import com.legacycrm.model.Lead;
import com.legacycrm.repo.LeadRepository;
import java.util.Optional;

/**
 * LeadService provides business operations for lead management in the CRM system.
 * 
 * Business Logic:
 * - Handles the creation and retrieval of leads (potential customers)
 * - Serves as the entry point for lead-related business operations
 * - Encapsulates lead creation business rules and validation
 * - Provides abstraction layer over lead data access
 * 
 * Lead Lifecycle Management:
 * - New leads are created with "New" status by default
 * - Lead data is validated and stored through repository pattern
 * - Supports lead retrieval for qualification and conversion processes
 * - Integrates with ConversionService for lead-to-opportunity workflow
 * 
 * Integration Points:
 * - Uses LeadRepository for data persistence
 * - Works with ConversionService for lead conversion process
 * - Could integrate with marketing automation systems for lead scoring
 * - May include lead validation and deduplication logic in future
 */
public class LeadService {
    private final LeadRepository leadRepository;

    public LeadService(LeadRepository leadRepository) {
        this.leadRepository = leadRepository;
    }

    /**
     * Creates a new lead in the CRM system.
     * 
     * Business Logic:
     * - Represents the initial capture of a potential customer's information
     * - Lead is created with "New" status indicating it requires qualification
     * - All contact information is captured for future marketing and sales activities
     * - Lead is automatically assigned a unique identifier for tracking
     * 
     * @param firstName Lead's first name
     * @param lastName Lead's last name
     * @param company Lead's company/organization
     * @param email Lead's email address (primary contact method)
     * @param phone Lead's phone number
     * @return The created and persisted Lead object
     */
    public Lead createLead(String firstName, String lastName, String company, String email, String phone) {
        Lead lead = new Lead(firstName, lastName, company, email, phone);
        return leadRepository.save(lead);
    }

    /**
     * Retrieves a lead by its unique identifier.
     * 
     * Business Logic:
     * - Used for lead qualification, conversion, and follow-up activities
     * - Returns Optional to handle cases where lead may not exist
     * - Supports lead lookup for conversion process and sales activities
     * 
     * @param id Unique identifier of the lead
     * @return Optional containing the lead if found, empty otherwise
     */
    public Optional<Lead> getLead(String id) {
        return leadRepository.findById(id);
    }
}
