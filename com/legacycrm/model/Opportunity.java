
package com.legacycrm.model;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

/**
 * Opportunity entity represents a qualified sales deal in the CRM system.
 * 
 * Business Logic:
 * - Opportunities are qualified leads that have been assessed as having real sales potential
 * - Each opportunity represents a potential revenue-generating deal with a specific monetary value
 * - Opportunities progress through various stages of the sales pipeline (e.g., "New Business", "Qualification", "Proposal", "Closed Won")
 * - They maintain a relationship to the originating lead for sales attribution and reporting
 * - Account name typically matches the lead's company but can be different for complex organizations
 * 
 * Sales Pipeline Stages (typical progression):
 * - "New Business": Initial opportunity creation
 * - "Qualification": Assessing customer needs and budget
 * - "Proposal": Formal proposal submitted
 * - "Negotiation": Contract terms being negotiated  
 * - "Closed Won": Deal successfully closed
 * - "Closed Lost": Deal lost to competitor or cancelled
 * 
 * Key Business Rules:
 * - Opportunity ID is immutable and auto-generated
 * - Amount should be positive (not enforced at entity level)
 * - Stage progression is managed by business logic in service layers
 * - Source lead ID maintains traceability for sales reporting
 * - All field updates automatically update the "updatedAt" timestamp
 */
public class Opportunity {
    private final String id;
    private String name;
    private double amount;
    private String stage; // e.g., "New Business", "Qualification", "Proposal", "Closed Won"
    private String accountName;
    private final Instant createdAt;
    private Instant updatedAt;
    private String sourceLeadId;

    /**
     * Creates a new Opportunity with the specified deal information.
     * 
     * Business Logic:
     * - Automatically generates a unique ID for the opportunity
     * - Records creation timestamp for audit and reporting purposes
     * - Links to source lead for sales attribution tracking
     * - Account name typically matches the lead's company
     * 
     * @param name Descriptive name for the opportunity (e.g., "Acme Corp - Software License")
     * @param amount Expected revenue value of the deal
     * @param stage Current stage in the sales pipeline
     * @param accountName Name of the customer account (usually from lead's company)
     * @param sourceLeadId ID of the lead that generated this opportunity
     */
    public Opportunity(String name, double amount, String stage, String accountName, String sourceLeadId) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.amount = amount;
        this.stage = stage;
        this.accountName = accountName;
        this.sourceLeadId = sourceLeadId;
        this.createdAt = Instant.now();
        this.updatedAt = this.createdAt;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public double getAmount() { return amount; }
    public String getStage() { return stage; }
    public String getAccountName() { return accountName; }
    public Instant getCreatedAt() { return createdAt; }
    public Instant getUpdatedAt() { return updatedAt; }
    public String getSourceLeadId() { return sourceLeadId; }

    public void setName(String name) { this.name = name; touch(); }
    public void setAmount(double amount) { this.amount = amount; touch(); }
    public void setStage(String stage) { this.stage = stage; touch(); }
    public void setAccountName(String accountName) { this.accountName = accountName; touch(); }

    /**
     * Updates the modification timestamp.
     * Called automatically whenever any opportunity field is modified.
     * This maintains an audit trail of when changes were made to track deal progression.
     */
    private void touch() { this.updatedAt = Instant.now(); }

    @Override
    public String toString() {
        return "Opportunity{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            ", amount=" + amount +
            ", stage='" + stage + '\'' +
            ", accountName='" + accountName + '\'' +
            ", sourceLeadId='" + sourceLeadId + '\'' +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Opportunity that = (Opportunity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
