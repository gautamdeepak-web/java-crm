
package com.legacycrm.model;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

/**
 * Represents a sales opportunity in the CRM system.
 * 
 * <p>An opportunity represents a qualified sales prospect with a potential monetary value.
 * Opportunities are typically created from converted leads and progress through various
 * stages of the sales pipeline until they are either won or lost.</p>
 * 
 * <h3>Key Features:</h3>
 * <ul>
 *   <li><b>Unique Identity:</b> Each opportunity has a UUID for tracking</li>
 *   <li><b>Financial Tracking:</b> Stores the potential monetary value</li>
 *   <li><b>Stage Management:</b> Tracks progression through sales pipeline</li>
 *   <li><b>Account Association:</b> Links to the target customer account</li>
 *   <li><b>Lead Traceability:</b> Maintains reference to the source lead</li>
 *   <li><b>Audit Trail:</b> Maintains creation and update timestamps</li>
 * </ul>
 * 
 * <h3>Business Rules:</h3>
 * <ul>
 *   <li>Opportunities must have a positive monetary amount</li>
 *   <li>Each opportunity is associated with exactly one account</li>
 *   <li>Opportunities can optionally reference a source lead</li>
 *   <li>Stage progression follows defined sales process</li>
 * </ul>
 * 
 * <h3>Typical Opportunity Lifecycle:</h3>
 * <ol>
 *   <li><b>Creation:</b> New opportunity from converted lead or direct entry</li>
 *   <li><b>Qualification:</b> Verify budget, authority, need, and timeline</li>
 *   <li><b>Proposal:</b> Present solution and negotiate terms</li>
 *   <li><b>Closure:</b> Final stage - either "Closed Won" or "Closed Lost"</li>
 * </ol>
 * 
 * <h3>Common Stages:</h3>
 * <ul>
 *   <li><b>New Business:</b> Initial opportunity identification</li>
 *   <li><b>Qualification:</b> Validating sales criteria</li>
 *   <li><b>Proposal:</b> Formal proposal submitted</li>
 *   <li><b>Negotiation:</b> Terms and pricing discussions</li>
 *   <li><b>Closed Won:</b> Successfully closed sale</li>
 *   <li><b>Closed Lost:</b> Opportunity did not convert to sale</li>
 * </ul>
 * 
 * @author CRM Development Team
 * @version 1.0
 * @since 1.0
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
     * Creates a new sales opportunity with the provided details.
     * 
     * <p>Initializes the opportunity with:</p>
     * <ul>
     *   <li>Auto-generated UUID as the unique identifier</li>
     *   <li>Current timestamp for creation and update times</li>
     *   <li>All provided business details</li>
     * </ul>
     * 
     * @param name descriptive name for the opportunity (e.g., "Acme Corp - Software License")
     * @param amount the potential monetary value of this opportunity
     * @param stage the current sales stage (e.g., "New Business", "Qualification", "Proposal")
     * @param accountName the name of the target customer account
     * @param sourceLeadId the ID of the lead that generated this opportunity (can be null for direct opportunities)
     * 
     * @throws IllegalArgumentException if name, stage, or accountName is null or empty
     * @throws IllegalArgumentException if amount is negative
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

    /** @return the unique identifier for this opportunity */
    public String getId() { return id; }
    
    /** @return the descriptive name of this opportunity */
    public String getName() { return name; }
    
    /** @return the potential monetary value of this opportunity */
    public double getAmount() { return amount; }
    
    /** @return the current sales stage of this opportunity */
    public String getStage() { return stage; }
    
    /** @return the name of the target customer account */
    public String getAccountName() { return accountName; }
    
    /** @return the timestamp when this opportunity was created */
    public Instant getCreatedAt() { return createdAt; }
    
    /** @return the timestamp when this opportunity was last updated */
    public Instant getUpdatedAt() { return updatedAt; }
    
    /** @return the ID of the lead that generated this opportunity, or null if created directly */
    public String getSourceLeadId() { return sourceLeadId; }

    /** 
     * Updates the opportunity name and refreshes the last updated timestamp.
     * @param name the new descriptive name for the opportunity
     */
    public void setName(String name) { this.name = name; touch(); }
    
    /** 
     * Updates the monetary amount and refreshes the last updated timestamp.
     * @param amount the new potential monetary value (must be non-negative)
     * @throws IllegalArgumentException if amount is negative
     */
    public void setAmount(double amount) { this.amount = amount; touch(); }
    
    /** 
     * Updates the sales stage and refreshes the last updated timestamp.
     * @param stage the new sales stage (e.g., "Qualification", "Proposal", "Closed Won")
     */
    public void setStage(String stage) { this.stage = stage; touch(); }
    
    /** 
     * Updates the account name and refreshes the last updated timestamp.
     * @param accountName the new target customer account name
     */
    public void setAccountName(String accountName) { this.accountName = accountName; touch(); }

    /**
     * Updates the last modified timestamp to the current time.
     * 
     * <p>This private utility method is called by all setter methods to maintain
     * an accurate audit trail of when the opportunity was last modified. This enables
     * tracking of opportunity activity and supports data synchronization scenarios.</p>
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
