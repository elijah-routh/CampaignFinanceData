package com.elijahrouth.campaignfinancedata.model;

import java.util.List;

public record FecCandidateResponse (
    List<FecCandidate> results
) { }
