
package com.legacycrm.service;

import com.legacycrm.model.Opportunity;
import com.legacycrm.repo.OpportunityRepository;

public class OpportunityService {
    private final OpportunityRepository opportunityRepository;

    public OpportunityService(OpportunityRepository opportunityRepository) {
        this.opportunityRepository = opportunityRepository;
    }

    public Opportunity createOpportunity(String name, double amount, String stage, String accountName, String sourceLeadId) {
        Opportunity opportunity = new Opportunity(name, amount, stage, accountName, sourceLeadId);
        return opportunityRepository.save(opportunity);
    }
}
