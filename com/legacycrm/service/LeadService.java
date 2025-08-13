
package com.legacycrm.service;

import com.legacycrm.model.Lead;
import com.legacycrm.repo.LeadRepository;
import java.util.Optional;

public class LeadService {
    private final LeadRepository leadRepository;

    public LeadService(LeadRepository leadRepository) {
        this.leadRepository = leadRepository;
    }

    public Lead createLead(String firstName, String lastName, String company, String email, String phone) {
        Lead lead = new Lead(firstName, lastName, company, email, phone);
        return leadRepository.save(lead);
    }

    public Optional<Lead> getLead(String id) {
        return leadRepository.findById(id);
    }
}
