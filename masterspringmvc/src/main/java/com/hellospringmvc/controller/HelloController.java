package com.hellospringmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {
    @RequestMapping("/")
    public String hello(Model model) {
        model.addAttribute("message", "Witaj w kontrolerze!");
        return "resultPage";
    }

    @RequestMapping("/param")
    public String hello(@RequestParam(defaultValue = "Stranger") String name, Model model) {
        model.addAttribute("message", "Hello, " + name + " !");
        return "param";
    }
}




