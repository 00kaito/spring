package com.coverter.app.converter.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class EmailExtractorController {
    @GetMapping("/email-extractor")
    public String domainExtractorGet(Model model){
        return "emailsextractor";
    }

    @PostMapping("/email-extractor")
    public String domainExtractorPost(@RequestParam("textemailextractor") String txt, Model model){
        ArrayList<String> emails = new ArrayList<String>();
        emails = EmailExtractorService.findEmails(txt);
        model.addAttribute("text", txt);
        model.addAttribute("emails", emails);
        model.addAttribute("size", emails.size());
        return "emailsextractor";
    }
}
