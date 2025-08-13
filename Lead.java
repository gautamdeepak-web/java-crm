
package com.legacycrm.model;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

/**
 * Lead entity represents a potential customer in the CRM system.
 * 
 * Business Logic:
 * - Leads are the starting point of the sales funnel, representing individuals or organizations
 *   that have expressed interest in products/services
 * - Each lead has a unique identifier and tracks contact information and current status
 * - Leads can be converted to Opportunities through the sales qualification process
 * - Once converted, leads maintain a reference to the resulting opportunity for tracking
 * - Status progression typically follows: "New" -> "Qualified" -> "Converted" 
 * - Conversion is a one-way operation - leads cannot be "unconverted"
 * - Audit trail is maintained through created/updated timestamps
 * 
 * Key Business Rules:
 * - Lead ID is immutable and auto-generated
 * - Email should be unique per lead (not enforced at entity level)
 * - Status defaults to "New" for all new leads
 * - Conversion process automatically updates status to "Converted"
 * - All field updates automatically update the "updatedAt" timestamp
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
     * Creates a new Lead with the specified contact information.
     * 
     * Business Logic:
     * - Automatically generates a unique ID for the lead
     * - Sets initial status to "New" indicating an unqualified lead
     * - Records creation timestamp for audit purposes
     * - Lead is not converted by default
     * 
     * @param firstName Lead's first name
     * @param lastName Lead's last name  
     * @param company Lead's company/organization
     * @param email Lead's email address (primary contact method)
     * @param phone Lead's phone number
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

    public String getId() { return id; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getCompany() { return company; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getStatus() { return status; }
    public Instant getCreatedAt() { return createdAt; }
    public Instant getUpdatedAt() { return updatedAt; }
    public boolean isConverted() { return converted; }
    public String getConvertedOpportunityId() { return convertedOpportunityId; }

    public void setFirstName(String firstName) { this.firstName = firstName; touch(); }
    public void setLastName(String lastName) { this.lastName = lastName; touch(); }
    public void setCompany(String company) { this.company = company; touch(); }
    public void setEmail(String email) { this.email = email; touch(); }
    public void setPhone(String phone) { this.phone = phone; touch(); }
    public void setStatus(String status) { this.status = status; touch(); }

    /**
     * Marks the lead as converted to an opportunity.
     * 
     * Business Logic:
     * - This is a critical business operation that represents successful lead qualification
     * - Once converted, the lead status becomes "Converted" and cannot be changed back
     * - Links the lead to the resulting opportunity for sales tracking and reporting
     * - Updates the timestamp to record when conversion occurred
     * - This operation is typically called only by ConversionService
     * 
     * @param opportunityId The ID of the opportunity created from this lead
     */
    public void markConverted(String opportunityId) {
        this.converted = true;
        this.convertedOpportunityId = opportunityId;
        this.status = "Converted";
        touch();
    }

    /**
     * Updates the modification timestamp.
     * Called automatically whenever any lead field is modified.
     * This maintains an audit trail of when changes were made.
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
