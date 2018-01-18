package com.coverter.app.converter.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.ArrayList;

@Controller
public class SitemapGenController {

    @GetMapping("/sitemap-generator")
    public String sitemapGet(Model model){
        return "sitemapgen";
    }

    @PostMapping("/sitemap-generator")
    public String sitemapPost(@RequestParam("url") String txt, Model model) throws IOException {
        ArrayList<String> sitemap  = new ArrayList<String>();
        sitemap = SitemMapGenService.start(txt);
        model.addAttribute("text", txt);
        model.addAttribute("demoinfo", " (Just a Demo)");
        model.addAttribute("sitemap", sitemap);
        model.addAttribute("size", sitemap.size());
        return "sitemapgen";
    }
}