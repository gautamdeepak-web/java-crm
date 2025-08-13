# Java CRM System - Business Logic Documentation

## Overview
This CRM (Customer Relationship Management) system manages the complete sales pipeline from lead capture to opportunity conversion. The system follows clean architecture principles with clear separation between entities, services, and data access layers.

## Core Business Entities

### Lead
- **Purpose**: Represents potential customers captured through marketing activities
- **Lifecycle**: New → Qualified → Converted
- **Key Features**: Contact information, conversion tracking, audit trail
- **Business Rules**: Unique ID, immutable once converted, automatic timestamp updates

### Opportunity
- **Purpose**: Represents qualified sales deals with revenue potential
- **Lifecycle**: New Business → Qualification → Proposal → Closed Won/Lost
- **Key Features**: Deal value, sales stage, source lead attribution
- **Business Rules**: Must link to source lead, positive deal amounts, stage progression tracking

## Core Business Processes

### Lead Management
- **Lead Creation**: Captures potential customer information with "New" status
- **Lead Qualification**: Assessment process to determine conversion potential
- **Lead Retrieval**: Lookup operations for sales team workflows

### Opportunity Management
- **Opportunity Creation**: Generates qualified deals from converted leads
- **Pipeline Tracking**: Monitors deals through sales stages
- **Revenue Forecasting**: Supports sales projections based on deal amounts and stages

### Lead-to-Opportunity Conversion
- **Critical Business Process**: Transforms marketing leads into sales opportunities
- **Business Rules**: Lead must exist and not be already converted
- **Data Integrity**: Maintains bidirectional relationships between leads and opportunities
- **Audit Trail**: Records conversion timestamps and attribution

## Architecture Components

### Service Layer
- **LeadService**: Business operations for lead management
- **OpportunityService**: Business operations for opportunity management  
- **ConversionService**: Complex business logic for lead conversion workflow

### Repository Layer
- **Interface-Based Design**: Enables multiple storage implementations
- **Current Implementation**: In-memory for development and testing
- **Production Ready**: Can be swapped for database implementations

### Application Entry Point
- **MainApp**: Demonstrates complete business workflow
- **Integration Example**: Shows proper dependency injection and service interaction

## Business Value

This CRM system enables organizations to:
1. **Track Lead Sources**: Understand which marketing activities generate qualified leads
2. **Measure Conversion Rates**: Calculate lead-to-opportunity conversion percentages
3. **Forecast Revenue**: Project sales based on opportunity pipeline
4. **Sales Attribution**: Track which leads generate actual revenue
5. **Pipeline Management**: Monitor deal progression through sales stages

## Technical Considerations

### Current State
- In-memory storage suitable for development and demos
- Single-threaded design for simplicity
- No external dependencies (except SalesforceAuth which is excluded from this documentation)

### Production Enhancements
- Database persistence layer
- Concurrent access protection
- REST API for web/mobile access
- Enhanced validation and error handling
- Logging and monitoring integration

## Files Documented
- Lead.java - Lead entity and business rules
- Opportunity.java - Opportunity entity and lifecycle
- ConversionService.java - Lead conversion business logic
- LeadService.java - Lead management operations
- OpportunityService.java - Opportunity management operations
- LeadRepository.java - Lead data access interface
- OpportunityRepository.java - Opportunity data access interface
- InMemoryLeadRepository.java - In-memory lead storage implementation
- InMemoryOpportunityRepository.java - In-memory opportunity storage implementation
- MainApp.java - Application workflow demonstration

## Files Excluded
- SalesforceAuth.java - External integration component (excluded as requested)