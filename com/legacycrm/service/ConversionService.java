
package com.legacycrm.service;

import com.legacycrm.model.Lead;
import com.legacycrm.model.Opportunity;
import com.legacycrm.repo.LeadRepository;

/**
 * ConversionService handles the critical business process of converting leads to opportunities.
 * 
 * Business Logic:
 * - This service encapsulates the lead qualification and conversion workflow
 * - Converting a lead to an opportunity represents a significant milestone in the sales process
 * - The conversion process maintains data integrity and proper business relationships
 * - This is typically used when a lead has been qualified and shows genuine sales potential
 * 
 * Key Business Operations:
 * - Validates lead exists and is eligible for conversion
 * - Creates opportunity with proper attribution to source lead
 * - Updates lead status to maintain accurate pipeline reporting
 * - Ensures atomic operation (both lead and opportunity are updated consistently)
 * 
 * Integration Points:
 * - Works with LeadRepository for lead data management
 * - Works with OpportunityService for opportunity creation
 * - Maintains referential integrity between leads and opportunities
 */
public class ConversionService {
    private final LeadRepository leadRepository;
    private final OpportunityService opportunityService;

    public ConversionService(LeadRepository leadRepository, OpportunityService opportunityService) {
        this.leadRepository = leadRepository;
        this.opportunityService = opportunityService;
    }

    /**
     * Convert a Lead into an Opportunity.
     * 
     * This is the core business operation for lead qualification and sales pipeline progression.
     * 
     * Business Rules:
     * - Lead must exist in the system
     * - Lead must not already be converted (prevents duplicate opportunities)
     * - Creates opportunity with lead's company as the account name
     * - Maintains bidirectional relationship (lead -> opportunity, opportunity -> source lead)
     * - Updates lead status to "Converted" for accurate pipeline reporting
     * - Operation is atomic - both lead and opportunity are updated or neither is
     * 
     * Sales Process Integration:
     * - This method is typically called after lead qualification activities
     * - Represents the transition from marketing qualified lead (MQL) to sales qualified opportunity
     * - Enables proper sales attribution and commission calculations
     * - Supports sales funnel reporting and analytics
     * 
     * @param leadId ID of the lead to convert
     * @param oppName Descriptive name for the new opportunity
     * @param amount Expected deal value
     * @param initialStage Starting stage for the opportunity (e.g., "New Business")
     * @return The created Opportunity
     * @throws IllegalArgumentException if lead doesn't exist
     * @throws IllegalStateException if lead is already converted
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
