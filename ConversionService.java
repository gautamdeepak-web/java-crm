
package com.legacycrm.service;

import com.legacycrm.model.Lead;
import com.legacycrm.model.Opportunity;
import com.legacycrm.repo.LeadRepository;

public class ConversionService {
    private final LeadRepository leadRepository;
    private final OpportunityService opportunityService;

    public ConversionService(LeadRepository leadRepository, OpportunityService opportunityService) {
        this.leadRepository = leadRepository;
        this.opportunityService = opportunityService;
    }

    /**
     * Convert a Lead into an Opportunity.
     * Business rules (customize as needed):
     * - Lead must exist and not already be converted.
     * - Create Opportunity referencing the Lead.
     * - Mark Lead as converted with the created Opportunity ID.
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
