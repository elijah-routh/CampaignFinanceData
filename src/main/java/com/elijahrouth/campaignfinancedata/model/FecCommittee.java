package com.elijahrouth.campaignfinancedata.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record FecCommittee (

    String name,

    @JsonProperty("party_full")
    String party,

    @JsonProperty("organization_type_full")
    String organization,

    @JsonProperty("committee_type_full")
    String committeeType,

    String state
) {}
