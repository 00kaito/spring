package com.demoproject.mydemo.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {

        return "testForm";
    }

    @PostMapping("/login")
    public String loginPost(@RequestParam("login") String login,
                            @RequestParam("password") String password,
                            Model model) {
        if (login.equals("admin") && password.equals("admin")) {
            model.addAttribute("error", "Zalogowano poprawnie!");
        } else {
            model.addAttribute("error", "Dane sa niepoprawne");
        }
        return "testForm";
    }
}