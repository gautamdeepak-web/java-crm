
package com.legacycrm;

import com.legacycrm.model.Lead;
import com.legacycrm.model.Opportunity;
import com.legacycrm.repo.InMemoryLeadRepository;
import com.legacycrm.repo.InMemoryOpportunityRepository;
import com.legacycrm.service.ConversionService;
import com.legacycrm.service.LeadService;
import com.legacycrm.service.OpportunityService;

public class MainApp {
    public static void main(String[] args) {
        // Initialize repositories (in-memory for demo; swap with DB-backed implementations later)
        InMemoryLeadRepository leadRepo = new InMemoryLeadRepository();
        InMemoryOpportunityRepository oppRepo = new InMemoryOpportunityRepository();

        // Initialize services
        LeadService leadService = new LeadService(leadRepo);
        OpportunityService opportunityService = new OpportunityService(oppRepo);
        ConversionService conversionService = new ConversionService(leadRepo, opportunityService);

        // 1) Create a Lead
        Lead lead = leadService.createLead("John", "Doe", "Acme Corp", "john.doe@acme.example", "+1-555-0100");
        System.out.println("Created Lead: " + lead);

        // 2) Convert Lead -> Opportunity
        Opportunity opp = conversionService.convertLeadToOpportunity(
            lead.getId(),
            "Acme Corp - New Deal",
            50000.00,
            "New Business"
        );

        System.out.println("Converted to Opportunity: " + opp);

        // 3) Verify the lead is marked as converted
        Lead fetched = leadService.getLead(lead.getId()).orElseThrow();
        System.out.println("Lead status after conversion: " + fetched.getStatus());
    }
}
