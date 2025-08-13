
package com.legacycrm.service;

import com.legacycrm.model.Opportunity;
import com.legacycrm.repo.OpportunityRepository;

/**
 * OpportunityService provides business operations for sales opportunity management.
 * 
 * Business Logic:
 * - Manages qualified sales opportunities that represent potential revenue
 * - Handles opportunity creation, typically from converted leads
 * - Encapsulates opportunity-specific business rules and validation
 * - Provides abstraction layer over opportunity data access
 * 
 * Sales Pipeline Management:
 * - Opportunities represent qualified deals in the sales pipeline
 * - Each opportunity has monetary value and sales stage tracking
 * - Supports sales forecasting and revenue projection activities
 * - Maintains attribution to source leads for sales reporting
 * 
 * Integration Points:
 * - Uses OpportunityRepository for data persistence
 * - Works with ConversionService during lead conversion process
 * - Could integrate with sales forecasting and reporting systems
 * - May include deal validation and pipeline management logic in future
 */
public class OpportunityService {
    private final OpportunityRepository opportunityRepository;

    public OpportunityService(OpportunityRepository opportunityRepository) {
        this.opportunityRepository = opportunityRepository;
    }

    /**
     * Creates a new opportunity in the CRM system.
     * 
     * Business Logic:
     * - Represents a qualified sales deal with revenue potential
     * - Opportunity is linked to its source lead for attribution tracking
     * - Account name typically matches the lead's company information
     * - Stage indicates current position in the sales pipeline
     * - Amount represents expected deal value for forecasting purposes
     * 
     * @param name Descriptive name for the opportunity
     * @param amount Expected revenue value of the deal
     * @param stage Current stage in the sales pipeline
     * @param accountName Name of the customer account
     * @param sourceLeadId ID of the lead that generated this opportunity
     * @return The created and persisted Opportunity object
     */
    public Opportunity createOpportunity(String name, double amount, String stage, String accountName, String sourceLeadId) {
        Opportunity opportunity = new Opportunity(name, amount, stage, accountName, sourceLeadId);
        return opportunityRepository.save(opportunity);
    }
}
