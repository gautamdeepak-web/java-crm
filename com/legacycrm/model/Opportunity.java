
package com.legacycrm.model;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

public class Opportunity {
    private final String id;
    private String name;
    private double amount;
    private String stage; // e.g., "New Business", "Qualification", "Proposal", "Closed Won"
    private String accountName;
    private final Instant createdAt;
    private Instant updatedAt;
    private String sourceLeadId;

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
