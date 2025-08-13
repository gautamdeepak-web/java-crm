/**
 * Root application package for the Legacy CRM system.
 * 
 * <p>This package contains the main application entry point and serves as the
 * top-level namespace for the Customer Relationship Management system. The
 * application demonstrates a complete CRM workflow from lead creation through
 * opportunity conversion.</p>
 * 
 * <h3>Application Overview:</h3>
 * <p>The Legacy CRM is a demonstration application showcasing fundamental
 * CRM concepts and clean architecture principles. It implements a simplified
 * but complete sales process management system.</p>
 * 
 * <h3>Main Application:</h3>
 * <ul>
 *   <li><b>{@link com.legacycrm.MainApp}:</b> Application entry point and workflow demonstration</li>
 * </ul>
 * 
 * <h3>System Architecture:</h3>
 * <p>The application follows a layered architecture pattern:</p>
 * <ul>
 *   <li><b>Application Layer:</b> {@code com.legacycrm} - Entry point and orchestration</li>
 *   <li><b>Service Layer:</b> {@code com.legacycrm.service} - Business logic and workflows</li>
 *   <li><b>Repository Layer:</b> {@code com.legacycrm.repo} - Data access abstraction</li>
 *   <li><b>Domain Layer:</b> {@code com.legacycrm.model} - Core business entities</li>
 * </ul>
 * 
 * <h3>Core Business Process:</h3>
 * <ol>
 *   <li><b>Lead Generation:</b> Potential customers express interest</li>
 *   <li><b>Lead Qualification:</b> Sales team evaluates lead quality</li>
 *   <li><b>Opportunity Creation:</b> Qualified leads become sales opportunities</li>
 *   <li><b>Sales Process:</b> Opportunities progress through sales stages</li>
 *   <li><b>Deal Closure:</b> Opportunities result in won or lost sales</li>
 * </ol>
 * 
 * <h3>Key Features Demonstrated:</h3>
 * <ul>
 *   <li><b>Contact Management:</b> Store and manage lead contact information</li>
 *   <li><b>Lead Tracking:</b> Monitor lead status and conversion eligibility</li>
 *   <li><b>Opportunity Management:</b> Track sales opportunities with monetary values</li>
 *   <li><b>Conversion Process:</b> Transform qualified leads into opportunities</li>
 *   <li><b>Data Integrity:</b> Maintain consistent state across related entities</li>
 * </ul>
 * 
 * <h3>Design Patterns:</h3>
 * <p>The application demonstrates several important design patterns:</p>
 * <ul>
 *   <li><b>Repository Pattern:</b> Abstract data access from business logic</li>
 *   <li><b>Service Layer Pattern:</b> Encapsulate business logic in services</li>
 *   <li><b>Dependency Injection:</b> Promote loose coupling and testability</li>
 *   <li><b>Domain Model Pattern:</b> Rich domain objects with behavior</li>
 * </ul>
 * 
 * <h3>Technology Stack:</h3>
 * <ul>
 *   <li><b>Language:</b> Java (core language features)</li>
 *   <li><b>Storage:</b> In-memory repositories (suitable for demonstration)</li>
 *   <li><b>Architecture:</b> Layered architecture with clear separation of concerns</li>
 *   <li><b>Dependencies:</b> Minimal external dependencies for simplicity</li>
 * </ul>
 * 
 * <h3>Usage:</h3>
 * <p>To run the demonstration:</p>
 * <pre>{@code
 * java com.legacycrm.MainApp
 * }</pre>
 * 
 * <h3>Production Readiness:</h3>
 * <p>For production deployment, consider enhancing with:</p>
 * <ul>
 *   <li><b>Database Integration:</b> Replace in-memory storage with databases</li>
 *   <li><b>REST API:</b> Add HTTP endpoints for external integration</li>
 *   <li><b>Security:</b> Implement authentication and authorization</li>
 *   <li><b>Configuration:</b> External configuration management</li>
 *   <li><b>Logging:</b> Comprehensive logging and monitoring</li>
 *   <li><b>Error Handling:</b> Global exception handling strategies</li>
 * </ul>
 * 
 * @version 1.0
 * @since 1.0
 */
package com.legacycrm;