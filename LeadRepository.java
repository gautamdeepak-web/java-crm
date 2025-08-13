
package com.legacycrm.repo;

import com.legacycrm.model.Lead;
import java.util.List;
import java.util.Optional;

public interface LeadRepository {
    Lead save(Lead lead);
    Optional<Lead> findById(String id);
    List<Lead> findAll();
    void deleteById(String id);
}
