package com.elijahrouth.campaignfinancedata.service;

import com.elijahrouth.campaignfinancedata.model.FecCandidate;
import com.elijahrouth.campaignfinancedata.model.FecCandidateResponse;
import com.elijahrouth.campaignfinancedata.model.FecCommittee;
import com.elijahrouth.campaignfinancedata.model.FecCommitteeResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service 
public class FecService {
    private final RestClient restClient;
    private final String apiKey;


    //set up API base URL using API key.
    public FecService(@Value("${fec.api-key}") String apiKey) {
        this.apiKey = apiKey;

        this.restClient = RestClient.builder().baseUrl("https://api.open.fec.gov/v1").build();
    }

    public List<FecCandidate> searchCandidates(String name) {
        //add onto base Url
        FecCandidateResponse response = restClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/candidates/search/")
                        .queryParam("q", name)
                        .queryParam("api_key", apiKey)
                        .queryParam("per_page", 20)
                        .build())
                .retrieve() //get API JSON results
                .body(FecCandidateResponse.class); // turn JSON into Java via FecCandidateResponse model.

        if (response == null || response.results() == null) {
            return List.of();
        }

        return response.results();
    }

    public List<FecCommittee> searchCommittees (String name) {
        FecCommitteeResponse response = restClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/committees/")
                        .queryParam("q", name)
                        .queryParam("api_key", apiKey)
                        .queryParam("per_page", 20)
                        .build())
                .retrieve() //get API JSON results
                .body(FecCommitteeResponse.class); // turn JSON into Java via FecCandidateResponse model.

        if (response == null || response.results() == null) {
            return List.of();
        }

        return response.results();
    }
}
