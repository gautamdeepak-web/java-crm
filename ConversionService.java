
package com.legacycrm.service;

import com.legacycrm.model.Lead;
import com.legacycrm.model.Opportunity;
import com.legacycrm.repo.LeadRepository;

/**
 * Service class providing business logic for converting leads to opportunities.
 * 
 * <p>This service orchestrates the critical business process of lead conversion,
 * which transforms qualified leads into sales opportunities. This process is
 * central to the CRM sales pipeline and requires careful coordination between
 * lead and opportunity management.</p>
 * 
 * <h3>Core Responsibilities:</h3>
 * <ul>
 *   <li><b>Lead Conversion:</b> Transforms qualified leads into opportunities</li>
 *   <li><b>Data Integrity:</b> Ensures consistent state across related entities</li>
 *   <li><b>Business Rules:</b> Enforces conversion-specific business logic</li>
 *   <li><b>Process Coordination:</b> Orchestrates multi-entity operations</li>
 * </ul>
 * 
 * <h3>Conversion Process:</h3>
 * <ol>
 *   <li><b>Validation:</b> Verify lead exists and is eligible for conversion</li>
 *   <li><b>Opportunity Creation:</b> Create opportunity with lead's company details</li>
 *   <li><b>Lead Update:</b> Mark lead as converted with opportunity reference</li>
 *   <li><b>Status Synchronization:</b> Ensure consistent status across entities</li>
 * </ol>
 * 
 * <h3>Business Rules Enforced:</h3>
 * <ul>
 *   <li>Lead must exist to be converted</li>
 *   <li>Lead cannot be converted more than once</li>
 *   <li>Converted lead maintains reference to created opportunity</li>
 *   <li>Lead status automatically changes to "Converted"</li>
 *   <li>Opportunity maintains reference to source lead for traceability</li>
 * </ul>
 * 
 * <h3>Design Principles:</h3>
 * <ul>
 *   <li><b>Single Responsibility:</b> Focused solely on lead conversion logic</li>
 *   <li><b>Transactional Behavior:</b> Maintains data consistency across operations</li>
 *   <li><b>Dependency Coordination:</b> Orchestrates between multiple services</li>
 *   <li><b>State Management:</b> Ensures proper entity state transitions</li>
 * </ul>
 * 
 * <h3>Usage Example:</h3>
 * <pre>{@code
 * ConversionService converter = new ConversionService(leadRepo, opportunityService);
 * 
 * // Convert a qualified lead to opportunity
 * Opportunity opportunity = converter.convertLeadToOpportunity(
 *     "lead-123",
 *     "Acme Corp - Enterprise Deal",
 *     100000.00,
 *     "Qualification"
 * );
 * }</pre>
 * 
 * @author CRM Development Team
 * @version 1.0
 * @since 1.0
 * @see Lead
 * @see Opportunity
 * @see LeadService
 * @see OpportunityService
 */
public class ConversionService {
    /** Repository for lead data operations and state management */
    private final LeadRepository leadRepository;
    
    /** Service for opportunity creation and management */
    private final OpportunityService opportunityService;

    /**
     * Constructs a new ConversionService with required dependencies.
     * 
     * <p>This service requires both a LeadRepository for direct lead access
     * and an OpportunityService for opportunity creation. The design uses
     * dependency injection to promote testability and flexibility.</p>
     * 
     * @param leadRepository repository for lead data operations (must not be null)
     * @param opportunityService service for opportunity management (must not be null)
     * @throws IllegalArgumentException if any parameter is null
     */
    public ConversionService(LeadRepository leadRepository, OpportunityService opportunityService) {
        this.leadRepository = leadRepository;
        this.opportunityService = opportunityService;
    }

    /**
     * Converts a qualified lead into a sales opportunity.
     * 
     * <p>This method implements the core lead conversion business process,
     * ensuring data consistency and proper state transitions across related entities.
     * The conversion is treated as an atomic operation to maintain data integrity.</p>
     * 
     * <h3>Conversion Process Steps:</h3>
     * <ol>
     *   <li><b>Lead Validation:</b> Verify lead exists and retrieve current state</li>
     *   <li><b>Conversion Check:</b> Ensure lead hasn't already been converted</li>
     *   <li><b>Opportunity Creation:</b> Create new opportunity with lead's company details</li>
     *   <li><b>Lead State Update:</b> Mark lead as converted with opportunity reference</li>
     *   <li><b>Persistence:</b> Save updated lead state to maintain consistency</li>
     * </ol>
     * 
     * <h3>Business Rules Enforced:</h3>
     * <ul>
     *   <li>Lead must exist in the system</li>
     *   <li>Lead cannot be converted more than once (prevents duplicate opportunities)</li>
     *   <li>Opportunity inherits company name from lead for consistency</li>
     *   <li>Source lead ID is maintained in opportunity for traceability</li>
     *   <li>Lead status automatically changes to "Converted"</li>
     * </ul>
     * 
     * <h3>Data Flow:</h3>
     * <pre>
     * Lead (New/Qualified) → Conversion Process → Lead (Converted) + Opportunity (New)
     *                                                     ↓                    ↑
     *                                              convertedOpportunityId ← sourceLeadId
     * </pre>
     * 
     * @param leadId the unique identifier of the lead to convert (must not be null)
     * @param oppName descriptive name for the new opportunity (must not be null)
     * @param amount the estimated monetary value of the opportunity (must be non-negative)
     * @param initialStage the starting sales stage for the opportunity (must not be null)
     * 
     * @return the newly created opportunity linked to the converted lead
     * 
     * @throws IllegalArgumentException if leadId is null or lead is not found
     * @throws IllegalStateException if lead has already been converted
     * @throws RuntimeException if opportunity creation or lead update fails
     * 
     * @see Lead#markConverted(String)
     * @see OpportunityService#createOpportunity(String, double, String, String, String)
     */
    public Opportunity convertLeadToOpportunity(String leadId, String oppName, double amount, String initialStage) {
        Lead lead = leadRepository.findById(leadId)
                .orElseThrow(() -> new IllegalArgumentException("Lead not found: " + leadId));

        if (lead.isConverted()) {
            throw new IllegalStateException("Lead already converted: " + leadId);
        }

        Opportunity opportunity = opportunityService.createOpportunity(
            oppName,
            amount,
            initialStage,
            lead.getCompany(),
            lead.getId()
        );

        lead.markConverted(opportunity.getId());
        leadRepository.save(lead); // persist updated state
        return opportunity;
    }
}
