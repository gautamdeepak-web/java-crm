
package com.legacycrm;

import com.legacycrm.model.Lead;
import com.legacycrm.model.Opportunity;
import com.legacycrm.repo.InMemoryLeadRepository;
import com.legacycrm.repo.InMemoryOpportunityRepository;
import com.legacycrm.service.ConversionService;
import com.legacycrm.service.LeadService;
import com.legacycrm.service.OpportunityService;

/**
 * MainApp demonstrates the core business workflow of the CRM system.
 * 
 * Business Workflow Demonstration:
 * - Lead Creation: Captures a potential customer's information
 * - Lead Qualification: Evaluates the lead for conversion potential
 * - Lead Conversion: Transforms qualified lead into a sales opportunity
 * - Pipeline Tracking: Shows how leads and opportunities are linked
 * 
 * This application demonstrates the complete lead-to-opportunity conversion process,
 * which is the fundamental business operation of the CRM system. It shows how:
 * 
 * 1. Marketing generates leads (potential customers)
 * 2. Sales qualifies leads for conversion potential
 * 3. Qualified leads become opportunities with revenue potential
 * 4. The system maintains proper data relationships and audit trails
 * 
 * Architecture Demonstration:
 * - Dependency injection pattern with service and repository layers
 * - Separation of concerns between business logic and data access
 * - Clean architecture principles with interface-based design
 * - In-memory implementations suitable for demo and testing
 * 
 * Production Considerations:
 * - Replace in-memory repositories with database implementations
 * - Add error handling and validation
 * - Implement proper logging and monitoring
 * - Add security and access control
 * - Consider adding REST API layer for web/mobile access
 */
public class MainApp {
    /**
     * Demonstrates the complete CRM business workflow from lead creation to opportunity conversion.
     * 
     * Business Process Flow:
     * 1. Lead Creation - Simulates marketing capturing a potential customer
     * 2. Lead Conversion - Simulates sales qualifying and converting the lead
     * 3. Status Verification - Confirms the lead has been properly converted
     * 
     * This workflow represents the core value proposition of the CRM system:
     * transforming marketing leads into qualified sales opportunities.
     */
    public static void main(String[] args) {
        // Initialize repositories (in-memory for demo; swap with DB-backed implementations later)
        InMemoryLeadRepository leadRepo = new InMemoryLeadRepository();
        InMemoryOpportunityRepository oppRepo = new InMemoryOpportunityRepository();

        // Initialize services
        LeadService leadService = new LeadService(leadRepo);
        OpportunityService opportunityService = new OpportunityService(oppRepo);
        ConversionService conversionService = new ConversionService(leadRepo, opportunityService);

        // 1) Create a Lead - represents marketing capturing a potential customer
        Lead lead = leadService.createLead("John", "Doe", "Acme Corp", "john.doe@acme.example", "+1-555-0100");
        System.out.println("Created Lead: " + lead);

        // 2) Convert Lead -> Opportunity - represents sales qualification process
        Opportunity opp = conversionService.convertLeadToOpportunity(
            lead.getId(),
            "Acme Corp - New Deal",
            50000.00,
            "New Business"
        );

        System.out.println("Converted to Opportunity: " + opp);

        // 3) Verify the lead is marked as converted - confirms proper business logic execution
        Lead fetched = leadService.getLead(lead.getId()).orElseThrow();
        System.out.println("Lead status after conversion: " + fetched.getStatus());
    }
}
