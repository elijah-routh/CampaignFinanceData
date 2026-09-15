package com.elijahrouth.campaignfinancedata.service;

import com.elijahrouth.campaignfinancedata.model.FecCandidate;
import com.elijahrouth.campaignfinancedata.model.FecCandidateResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service 
public class FecService {
    private final RestClient restClient;
    private final String apiKey;

    public FecService(@Value("${fec.api-key}") String apiKey) {
        this.apiKey = apiKey;

        this.restClient = RestClient.builder().baseUrl("https://api.open.fec.gov/v1").build();
    }

    public List<FecCandidate> searchCandidates(String name) {
        FecCandidateResponse response = restClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/candidates/search/")
                        .queryParam("q", name)
                        .queryParam("api_key", apiKey)
                        .queryParam("per_page", 20)
                        .build())
                .retrieve()
                .body(FecCandidateResponse.class);

        if (response == null || response.results() == null) {
            return List.of();
        }

        return response.results();
    }
}
