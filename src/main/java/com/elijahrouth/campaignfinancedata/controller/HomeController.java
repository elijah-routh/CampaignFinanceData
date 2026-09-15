package com.elijahrouth.campaignfinancedata.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.elijahrouth.campaignfinancedata.service.FecService;
import com.elijahrouth.campaignfinancedata.model.FecCandidate;
import org.springframework.ui.Model;

import java.util.List;

@Controller 
public class HomeController {

    private final FecService fecService;

    public HomeController(FecService fecService){
        this.fecService = fecService;
    }

    @GetMapping("/")
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
}
