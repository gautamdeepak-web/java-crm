package com.example.salesforce;

import org.json.JSONObject;

public class MainApp {
    public static void main(String[] args) {
        try {
            // Salesforce credentials
            String clientId = "YOUR_CLIENT_ID";
            String clientSecret = "YOUR_CLIENT_SECRET";
            String username = "YOUR_SF_USERNAME";
            String password = "YOUR_SF_PASSWORD+SECURITY_TOKEN";
            String loginUrl = "https://login.salesforce.com";

            // Step 1: Authenticate
            SalesforceAuth auth = new SalesforceAuth(clientId, clientSecret, username, password, loginUrl);
            JSONObject authResponse = auth.authenticate();
            String accessToken = authResponse.getString("access_token");
            String instanceUrl = authResponse.getString("instance_url");

            // Step 2: Create Lead
            LeadService leadService = new LeadService(instanceUrl, accessToken);
            String leadId = leadService.createLead("John", "Doe", "Acme Corp");
            System.out.println("Lead created with ID: " + leadId);

            // Step 3: Convert Lead
            leadService.convertLead(leadId);
            System.out.println("Lead converted to Opportunity.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
