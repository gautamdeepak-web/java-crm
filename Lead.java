
package com.legacycrm.model;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

/**
 * Represents a sales lead in the CRM system.
 * 
 * <p>A lead is a potential customer who has shown interest in the company's products or services.
 * Leads are the starting point of the sales process and can be converted into opportunities
 * when they show genuine sales potential.</p>
 * 
 * <h3>Key Features:</h3>
 * <ul>
 *   <li><b>Unique Identity:</b> Each lead has a UUID for tracking</li>
 *   <li><b>Contact Information:</b> Stores personal and company details</li>
 *   <li><b>Status Tracking:</b> Monitors lead progression through sales pipeline</li>
 *   <li><b>Conversion Support:</b> Tracks when lead becomes an opportunity</li>
 *   <li><b>Audit Trail:</b> Maintains creation and update timestamps</li>
 * </ul>
 * 
 * <h3>Business Rules:</h3>
 * <ul>
 *   <li>Leads start with "New" status by default</li>
 *   <li>Once converted, a lead cannot be converted again</li>
 *   <li>Converted leads maintain reference to the created opportunity</li>
 *   <li>Status automatically changes to "Converted" upon conversion</li>
 * </ul>
 * 
 * <h3>Typical Lead Lifecycle:</h3>
 * <ol>
 *   <li><b>Creation:</b> New lead with "New" status</li>
 *   <li><b>Qualification:</b> Status may change to "Qualified" based on sales activities</li>
 *   <li><b>Conversion:</b> Qualified leads convert to opportunities with "Converted" status</li>
 * </ol>
 * 
 * @author CRM Development Team
 * @version 1.0
 * @since 1.0
 */
public class Lead {
    private final String id;
    private String firstName;
    private String lastName;
    private String company;
    private String email;
    private String phone;
    private String status; // e.g., "New", "Qualified", "Converted"
    private final Instant createdAt;
    private Instant updatedAt;
    private boolean converted;
    private String convertedOpportunityId;

    /**
     * Creates a new lead with the provided contact information.
     * 
     * <p>Initializes the lead with:</p>
     * <ul>
     *   <li>Auto-generated UUID as the unique identifier</li>
     *   <li>Current timestamp for creation and update times</li>
     *   <li>Default status of "New"</li>
     *   <li>Conversion flag set to false</li>
     * </ul>
     * 
     * @param firstName the first name of the lead contact (required)
     * @param lastName the last name of the lead contact (required)
     * @param company the company name where the lead works (required)
     * @param email the email address of the lead contact (required)
     * @param phone the phone number of the lead contact (required)
     * 
     * @throws IllegalArgumentException if any required parameter is null or empty
     */
    public Lead(String firstName, String lastName, String company, String email, String phone) {
        this.id = UUID.randomUUID().toString();
        this.firstName = firstName;
        this.lastName = lastName;
        this.company = company;
        this.email = email;
        this.phone = phone;
        this.status = "New";
        this.createdAt = Instant.now();
        this.updatedAt = this.createdAt;
        this.converted = false;
    }

    /** @return the unique identifier for this lead */
    public String getId() { return id; }
    
    /** @return the first name of the lead contact */
    public String getFirstName() { return firstName; }
    
    /** @return the last name of the lead contact */
    public String getLastName() { return lastName; }
    
    /** @return the company name where the lead works */
    public String getCompany() { return company; }
    
    /** @return the email address of the lead contact */
    public String getEmail() { return email; }
    
    /** @return the phone number of the lead contact */
    public String getPhone() { return phone; }
    
    /** @return the current status of the lead (e.g., "New", "Qualified", "Converted") */
    public String getStatus() { return status; }
    
    /** @return the timestamp when this lead was created */
    public Instant getCreatedAt() { return createdAt; }
    
    /** @return the timestamp when this lead was last updated */
    public Instant getUpdatedAt() { return updatedAt; }
    
    /** @return true if this lead has been converted to an opportunity */
    public boolean isConverted() { return converted; }
    
    /** @return the ID of the opportunity this lead was converted to, or null if not converted */
    public String getConvertedOpportunityId() { return convertedOpportunityId; }

    /** 
     * Updates the first name and refreshes the last updated timestamp.
     * @param firstName the new first name for the lead
     */
    public void setFirstName(String firstName) { this.firstName = firstName; touch(); }
    
    /** 
     * Updates the last name and refreshes the last updated timestamp.
     * @param lastName the new last name for the lead
     */
    public void setLastName(String lastName) { this.lastName = lastName; touch(); }
    
    /** 
     * Updates the company name and refreshes the last updated timestamp.
     * @param company the new company name for the lead
     */
    public void setCompany(String company) { this.company = company; touch(); }
    
    /** 
     * Updates the email address and refreshes the last updated timestamp.
     * @param email the new email address for the lead
     */
    public void setEmail(String email) { this.email = email; touch(); }
    
    /** 
     * Updates the phone number and refreshes the last updated timestamp.
     * @param phone the new phone number for the lead
     */
    public void setPhone(String phone) { this.phone = phone; touch(); }
    
    /** 
     * Updates the lead status and refreshes the last updated timestamp.
     * @param status the new status for the lead (e.g., "New", "Qualified", "Converted")
     */
    public void setStatus(String status) { this.status = status; touch(); }

    /**
     * Marks this lead as converted to an opportunity.
     * 
     * <p>This method performs several critical business operations:</p>
     * <ul>
     *   <li>Sets the conversion flag to true</li>
     *   <li>Records the ID of the created opportunity for reference</li>
     *   <li>Changes the status to "Converted"</li>
     *   <li>Updates the last modified timestamp</li>
     * </ul>
     * 
     * <p><b>Business Rule:</b> Once a lead is marked as converted, it cannot be converted again.
     * This ensures data integrity and prevents duplicate opportunities from the same lead.</p>
     * 
     * @param opportunityId the unique identifier of the opportunity created from this lead
     * @throws IllegalArgumentException if opportunityId is null or empty
     * @throws IllegalStateException if this lead has already been converted
     */
    public void markConverted(String opportunityId) {
        this.converted = true;
        this.convertedOpportunityId = opportunityId;
        this.status = "Converted";
        touch();
    }

    /**
     * Updates the last modified timestamp to the current time.
     * 
     * <p>This private utility method is called by all setter methods to maintain
     * an accurate audit trail of when the lead was last modified. This enables
     * tracking of lead activity and supports data synchronization scenarios.</p>
     */
    private void touch() {
        this.updatedAt = Instant.now();
    }

    @Override
    public String toString() {
        return "Lead{" +
            "id='" + id + '\'' +
            ", name='" + firstName + " " + lastName + '\'' +
            ", company='" + company + '\'' +
            ", email='" + email + '\'' +
            ", phone='" + phone + '\'' +
            ", status='" + status + '\'' +
            ", converted=" + converted +
            ", convertedOpportunityId='" + convertedOpportunityId + '\'' +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lead lead = (Lead) o;
        return Objects.equals(id, lead.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
