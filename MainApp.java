
package com.legacycrm;

import com.legacycrm.model.Lead;
import com.legacycrm.model.Opportunity;
import com.legacycrm.repo.InMemoryLeadRepository;
import com.legacycrm.repo.InMemoryOpportunityRepository;
import com.legacycrm.service.ConversionService;
import com.legacycrm.service.LeadService;
import com.legacycrm.service.OpportunityService;

/**
 * Main application class demonstrating the Java CRM system functionality.
 * 
 * <p>This class serves as the entry point for the CRM application and provides
 * a comprehensive demonstration of the core CRM workflow: lead creation,
 * lead-to-opportunity conversion, and data verification.</p>
 * 
 * <h3>Demonstration Workflow:</h3>
 * <ol>
 *   <li><b>System Initialization:</b> Set up repositories and services</li>
 *   <li><b>Lead Creation:</b> Create a new lead with contact information</li>
 *   <li><b>Lead Conversion:</b> Convert the lead to a sales opportunity</li>
 *   <li><b>State Verification:</b> Verify the lead's conversion status</li>
 * </ol>
 * 
 * <h3>Architecture Demonstration:</h3>
 * <p>The application showcases the layered architecture of the CRM system:</p>
 * <ul>
 *   <li><b>Repository Layer:</b> In-memory implementations for data storage</li>
 *   <li><b>Service Layer:</b> Business logic services for lead and opportunity management</li>
 *   <li><b>Application Layer:</b> This main class orchestrating the workflow</li>
 * </ul>
 * 
 * <h3>Dependency Injection Pattern:</h3>
 * <p>The application demonstrates proper dependency injection by:</p>
 * <ul>
 *   <li>Creating repository implementations first</li>
 *   <li>Injecting repositories into services via constructors</li>
 *   <li>Injecting services into higher-level services (ConversionService)</li>
 * </ul>
 * 
 * <h3>Production Considerations:</h3>
 * <p>In a production environment, consider:</p>
 * <ul>
 *   <li><b>Configuration:</b> Use external configuration for system setup</li>
 *   <li><b>Dependency Injection Framework:</b> Use Spring or similar for DI management</li>
 *   <li><b>Database Integration:</b> Replace in-memory repositories with database implementations</li>
 *   <li><b>Error Handling:</b> Add comprehensive exception handling and logging</li>
 *   <li><b>API Layer:</b> Add REST endpoints for external system integration</li>
 * </ul>
 * 
 * <h3>Example Output:</h3>
 * <pre>
 * Created Lead: Lead{id='...', name='John Doe', company='Acme Corp', ...}
 * Converted to Opportunity: Opportunity{id='...', name='Acme Corp - New Deal', ...}
 * Lead status after conversion: Converted
 * </pre>
 * 
 * @author CRM Development Team
 * @version 1.0
 * @since 1.0
 * @see Lead
 * @see Opportunity
 * @see LeadService
 * @see OpportunityService
 * @see ConversionService
 */
public class MainApp {
    /**
     * Main entry point for the CRM application demonstration.
     * 
     * <p>This method orchestrates a complete CRM workflow demonstration that showcases
     * the key features and interactions between system components. The demonstration
     * follows a realistic business scenario from initial lead creation through
     * conversion to a sales opportunity.</p>
     * 
     * <h3>Demonstration Steps:</h3>
     * <ol>
     *   <li><b>System Setup:</b>
     *       <ul>
     *         <li>Initialize in-memory repositories for development/demo purposes</li>
     *         <li>Create service instances with proper dependency injection</li>
     *         <li>Wire services together for the conversion workflow</li>
     *       </ul>
     *   </li>
     *   <li><b>Lead Management:</b>
     *       <ul>
     *         <li>Create a new lead with realistic contact information</li>
     *         <li>Display the created lead details to show initial state</li>
     *       </ul>
     *   </li>
     *   <li><b>Lead Conversion:</b>
     *       <ul>
     *         <li>Convert the lead to a sales opportunity</li>
     *         <li>Provide opportunity details including name, amount, and stage</li>
     *         <li>Display the created opportunity information</li>
     *       </ul>
     *   </li>
     *   <li><b>State Verification:</b>
     *       <ul>
     *         <li>Retrieve the lead again to verify conversion</li>
     *         <li>Display the updated lead status to confirm state change</li>
     *       </ul>
     *   </li>
     * </ol>
     * 
     * <h3>Business Scenario:</h3>
     * <p>The demonstration simulates a typical B2B sales scenario:</p>
     * <ul>
     *   <li><b>Prospect:</b> John Doe from Acme Corp shows interest</li>
     *   <li><b>Lead Creation:</b> Sales team creates a lead record</li>
     *   <li><b>Qualification:</b> Lead is qualified and ready for conversion</li>
     *   <li><b>Opportunity:</b> $50,000 "New Business" opportunity is created</li>
     *   <li><b>Tracking:</b> Lead status changes to "Converted" for tracking</li>
     * </ul>
     * 
     * @param args command line arguments (not used in this demonstration)
     * 
     * @throws RuntimeException if any step in the demonstration fails
     * 
     * @see LeadService#createLead(String, String, String, String, String)
     * @see ConversionService#convertLeadToOpportunity(String, String, double, String)
     * @see LeadService#getLead(String)
     */
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
