
package com.legacycrm.model;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

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

    public void markConverted(String opportunityId) {
        this.converted = true;
        this.convertedOpportunityId = opportunityId;
        this.status = "Converted";
        touch();
    }

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
