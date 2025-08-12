package com.example.salesforce;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class LeadService {
    private String instanceUrl;
    private String accessToken;

    public LeadService(String instanceUrl, String accessToken) {
        this.instanceUrl = instanceUrl;
        this.accessToken = accessToken;
    }

    public String createLead(String firstName, String lastName, String company) throws Exception {
        URL url = new URL(instanceUrl + "/services/data/v57.0/sobjects/Lead/");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Authorization", "Bearer " + accessToken);
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        JSONObject lead = new JSONObject();
        lead.put("FirstName", firstName);
        lead.put("LastName", lastName);
        lead.put("Company", company);

        try (OutputStream os = conn.getOutputStream()) {
            os.write(lead.toString().getBytes());
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String output;
        StringBuffer response = new StringBuffer();
        while ((output = br.readLine()) != null) {
            response.append(output);
        }
        br.close();

        JSONObject jsonResponse = new JSONObject(response.toString());
        return jsonResponse.getString("id");
    }

    public void convertLead(String leadId) throws Exception {
        URL url = new URL(instanceUrl + "/services/data/v57.0/sobjects/Lead/" + leadId + "/leadConvert");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Authorization", "Bearer " + accessToken);
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        JSONObject convertRequest = new JSONObject();
        convertRequest.put("leadId", leadId);
        convertRequest.put("convertedStatus", "Closed - Converted");
        convertRequest.put("doNotCreateOpportunity", false);

        try (OutputStream os = conn.getOutputStream()) {
            os.write(convertRequest.toString().getBytes());
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String output;
        while ((output = br.readLine()) != null) {
            System.out.println(output);
        }
        br.close();
    }
}
