/**
 * Domain model package containing core business entities for the CRM system.
 * 
 * <p>This package contains the fundamental business objects that represent
 * the core concepts in the Customer Relationship Management domain. These
 * entities encapsulate both data and behavior related to the sales process.</p>
 * 
 * <h3>Core Entities:</h3>
 * <ul>
 *   <li><b>{@link com.legacycrm.model.Lead}:</b> Represents potential customers showing interest</li>
 *   <li><b>{@link com.legacycrm.model.Opportunity}:</b> Represents qualified sales prospects with monetary value</li>
 * </ul>
 * 
 * <h3>Design Principles:</h3>
 * <ul>
 *   <li><b>Domain-Driven Design:</b> Entities model real business concepts</li>
 *   <li><b>Encapsulation:</b> Business rules are encapsulated within entities</li>
 *   <li><b>Immutable IDs:</b> Each entity has an immutable unique identifier</li>
 *   <li><b>Audit Trails:</b> Entities track creation and modification timestamps</li>
 *   <li><b>State Management:</b> Entities manage their own state transitions</li>
 * </ul>
 * 
 * <h3>Entity Relationships:</h3>
 * <ul>
 *   <li><b>Lead → Opportunity:</b> Leads can be converted to Opportunities</li>
 *   <li><b>Bidirectional References:</b> Converted leads reference their opportunities</li>
 *   <li><b>Traceability:</b> Opportunities maintain reference to source leads</li>
 * </ul>
 * 
 * <h3>Business Rules:</h3>
 * <ul>
 *   <li>Leads start with "New" status and can progress through qualification</li>
 *   <li>Only non-converted leads can be converted to opportunities</li>
 *   <li>Opportunities must have positive monetary amounts</li>
 *   <li>Entity modifications automatically update timestamps</li>
 * </ul>
 * 
 * <h3>Usage Patterns:</h3>
 * <p>Entities in this package are typically:</p>
 * <ul>
 *   <li>Created by service layer business logic</li>
 *   <li>Persisted through repository abstractions</li>
 *   <li>Transferred between application layers</li>
 *   <li>Serialized for external system integration</li>
 * </ul>
 * 
 * @version 1.0
 * @since 1.0
 */
package com.legacycrm.model;