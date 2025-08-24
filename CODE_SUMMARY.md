# Java CRM Application - Code Logic Summary

## Overview
This is a simple Java-based Customer Relationship Management (CRM) application that integrates with Salesforce using REST APIs. The application demonstrates a complete lead management workflow: authentication, lead creation, and lead conversion to opportunities.

## Application Architecture

### High-Level Design
The application follows a **service-oriented architecture** with clear separation of concerns across three main components:

```
MainApp.java (Entry Point & Orchestration)
    ↓
SalesforceAuth.java (Authentication Service)
    ↓
LeadService.java (Lead Management Service)
    ↓
Salesforce REST API
```

### Core Components

#### 1. MainApp.java - Application Entry Point
**Purpose:** Orchestrates the entire lead management workflow

**Key Responsibilities:**
- Manages Salesforce credentials and configuration
- Coordinates authentication and service initialization
- Executes the main business process flow
- Handles top-level exception management

**Workflow Logic:**
1. **Configuration Setup**: Defines Salesforce credentials (client ID, secret, username, password)
2. **Authentication**: Creates SalesforceAuth instance and obtains access token
3. **Service Initialization**: Initializes LeadService with authentication details
4. **Lead Creation**: Creates a new lead with sample data (John Doe, Acme Corp)
5. **Lead Conversion**: Converts the created lead to an opportunity

#### 2. SalesforceAuth.java - Authentication Service
**Purpose:** Handles OAuth2 authentication with Salesforce

**Authentication Method:** OAuth2 Password Grant Flow
- **Grant Type**: `password`
- **Endpoint**: `/services/oauth2/token`
- **Parameters**: client_id, client_secret, username, password

**Key Features:**
- URL encoding of credentials for security
- HTTP POST request handling
- JSON response parsing
- Returns access token and instance URL

**Technical Implementation:**
- Uses `HttpURLConnection` for HTTP communication
- Implements proper resource management with try-with-resources
- Returns structured JSON response containing authentication details

#### 3. LeadService.java - Lead Management Service
**Purpose:** Manages lead lifecycle operations through Salesforce REST API

**API Version:** Salesforce REST API v57.0

**Core Operations:**

##### Create Lead (`createLead` method)
- **Endpoint**: `/services/data/v57.0/sobjects/Lead/`
- **Method**: POST
- **Payload**: JSON object with FirstName, LastName, Company
- **Returns**: Salesforce-generated Lead ID

##### Convert Lead (`convertLead` method)
- **Endpoint**: `/services/data/v57.0/sobjects/Lead/{leadId}/leadConvert`
- **Method**: POST
- **Payload**: JSON with leadId, convertedStatus, doNotCreateOpportunity flag
- **Result**: Converts lead to Account, Contact, and Opportunity

## Technical Implementation Details

### HTTP Communication
- **Library**: Java's built-in `HttpURLConnection`
- **Content-Type**: `application/json`
- **Authorization**: Bearer token authentication
- **Error Handling**: Exception-based error management

### Data Format
- **Input/Output**: JSON format using `org.json.JSONObject`
- **Encoding**: UTF-8 character encoding
- **Serialization**: Manual JSON object construction

### Security Considerations
- **Authentication**: OAuth2 with secure token handling
- **Credentials**: Placeholder values requiring actual Salesforce credentials
- **Encoding**: URL encoding for credential parameters

## Dependencies
- **org.json**: External library for JSON parsing and manipulation (Maven: `org.json:json:20230227`)
- **java.io.***: Standard I/O operations (built-in)
- **java.net.***: HTTP networking capabilities (built-in)

### Build Requirements
To compile and run this application, you need:
1. Java Development Kit (JDK) 8 or higher
2. The org.json library JAR file in the classpath
3. Valid Salesforce credentials and connected app setup

## Configuration Requirements
To run this application, the following Salesforce credentials must be provided:
- **Client ID**: Connected App client identifier
- **Client Secret**: Connected App client secret
- **Username**: Salesforce user login
- **Password + Security Token**: Combined password and security token
- **Login URL**: Salesforce instance URL (default: https://login.salesforce.com)

## Business Process Flow

```
1. Application Start
   ↓
2. Load Salesforce Credentials
   ↓
3. Authenticate with Salesforce (OAuth2)
   ↓
4. Receive Access Token & Instance URL
   ↓
5. Create Lead Record
   ↓
6. Convert Lead to Opportunity
   ↓
7. Process Complete
```

## Design Patterns Used

### 1. **Service Layer Pattern**
- SalesforceAuth and LeadService act as service layers
- Encapsulate external API communication
- Provide clean interfaces for business operations

### 2. **Constructor Dependency Injection**
- Services receive dependencies through constructors
- Promotes loose coupling and testability

### 3. **Resource Management**
- Try-with-resources for proper stream handling
- Automatic resource cleanup

## Error Handling Strategy
- **Exception Propagation**: Methods throw exceptions up the call stack
- **Top-Level Handling**: MainApp catches and prints stack traces
- **Resource Cleanup**: Proper closing of HTTP connections and streams

## Potential Improvements
1. **Configuration Management**: Externalize credentials to properties files
2. **Error Handling**: Implement more granular exception handling
3. **Logging**: Add structured logging instead of System.out.println
4. **Testing**: Add unit and integration tests
5. **Build Management**: Add Maven/Gradle build configuration
6. **Connection Pooling**: Implement HTTP connection pooling for better performance
7. **Retry Logic**: Add retry mechanisms for network failures

## Setup and Compilation Guide

### Prerequisites
1. Install Java Development Kit (JDK) 8 or higher
2. Download the org.json library JAR file
3. Set up a Salesforce Connected App with OAuth2 enabled

### Compilation Steps
```bash
# Download org.json library (example using Maven coordinates)
# org.json:json:20230227

# Compile the Java files
javac -cp ".:json-20230227.jar" *.java

# Run the application
java -cp ".:json-20230227.jar" com.example.salesforce.MainApp
```

### Configuration Steps
1. Replace placeholder credentials in `MainApp.java`:
   - `YOUR_CLIENT_ID`: Your Salesforce Connected App Client ID
   - `YOUR_CLIENT_SECRET`: Your Salesforce Connected App Client Secret  
   - `YOUR_SF_USERNAME`: Your Salesforce username
   - `YOUR_SF_PASSWORD+SECURITY_TOKEN`: Your password + security token

## Summary
This Java CRM application provides a foundational implementation of Salesforce integration focusing on lead management. It demonstrates proper separation of concerns, OAuth2 authentication, and RESTful API communication patterns. While functional, it serves as a starting point that could benefit from additional enterprise-grade features like configuration management, comprehensive error handling, and automated testing.