package com.elijahrouth.campaignfinancedata.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import com.elijahrouth.campaignfinancedata.service.FecService;
import com.elijahrouth.campaignfinancedata.model.FecCandidate;
import com.elijahrouth.campaignfinancedata.model.FecCommittee;

import org.springframework.ui.Model;

import java.util.List;

@Controller 
public class HomeController {

    private final FecService fecService;

    public HomeController(FecService fecService){
        this.fecService = fecService;
    }

    @GetMapping("/") // allows url to automatically find the matching controller method
    public String home() {
        return "index";
    }

    @GetMapping("/search")
    public String search(@RequestParam String name, Model model) {
        List<FecCandidate> candidates =
            fecService.searchCandidates(name);

        model.addAttribute("candidates", candidates);
        model.addAttribute("searchName", name);

        return "index";
    }

    @GetMapping("/searchCommittees")
    public String searchCommittees(@RequestParam String name, Model model) {
        List<FecCommittee> committees =
            fecService.searchCommittees(name);

        model.addAttribute("committees", committees);
        model.addAttribute("searchCommittees", name);

        return "index";
    }

    @GetMapping("/candidates/{id}")
    public String candidateDashboard(@PathVariable String id, Model model) {
        FecCandidate candidate = fecService.getCandidate(id);

        model.addAttribute("candidate", candidate);
        
        return "dashboard";
    }
}
