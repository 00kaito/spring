package com.coverter.app.converter.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class DomainExtractorController {
    @GetMapping("/domain-extractor")
    public String domainExtractorGet(Model model){
        return "domainsextractor";
    }

    @PostMapping("/domain-extractor")
    public String domainExtractorPost(@RequestParam ("textdomainextractor") String txt, Model model){
        ArrayList<String> domains = new ArrayList<String>();
        domains = DomainExtractorService.findDomains(txt);
        model.addAttribute("domains", domains);
        model.addAttribute("size", domains.size());
        return "domainsextractor";
    }
}
