package com.demoproject.mydemo.Controllers;

import com.demoproject.mydemo.Models.Forms.FooterContactFormModel;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class FooterFormController {
    String feedbackinfo = "Nothing to say";

    @RequestMapping(value = "/", method = RequestMethod.GET)
    //aby cokolwiek wyslac do templatki index w metodzie nalezy dodac argument typu Model model
    public String index(Model model) {
        model.addAttribute("contactForm", new FooterContactFormModel());
        model.addAttribute("feedbackmessage", "");
        model.addAttribute("visibility", false);
        return "index";
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public RedirectView redirect301()
    {
        String correctUrl = "/";
        RedirectView rv = new RedirectView(correctUrl);
        rv.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
        return rv;
    }

    @RequestMapping(value = "/elements", method = RequestMethod.GET)
    public String elements(Model model) {
        model.addAttribute("contactForm", new FooterContactFormModel());
        model.addAttribute("feedbackmessage", "");
        model.addAttribute("visibility", false);
        return "elements";
    }
    @RequestMapping(value = "/generic", method = RequestMethod.GET)
    public String generic(Model model) {
        model.addAttribute("contactForm", new FooterContactFormModel());
        model.addAttribute("feedbackmessage", "");
        model.addAttribute("visibility", false);
        return "generic";
    }
    @RequestMapping(value = "/landing", method = RequestMethod.GET)
    public String landing(Model model) {
        model.addAttribute("contactForm", new FooterContactFormModel());
        model.addAttribute("feedbackmessage", "");
        model.addAttribute("visibility", false);
        return "landing";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String index(@ModelAttribute("contactForm") FooterContactFormModel contactForm, Model model) {
        model.addAttribute("visibility", contactForm.issendingMessageFlag());
        feedbackinfo = SendEmailService.senditsimple(contactForm);
        model.addAttribute("feedbackmessage", feedbackinfo);
        return "index";
    }

    @RequestMapping(value = "/elements", method = RequestMethod.POST)
    public String elements(@ModelAttribute("contactForm") FooterContactFormModel contactForm, Model model) {
        model.addAttribute("visibility", contactForm.issendingMessageFlag());
        feedbackinfo = SendEmailService.senditsimple(contactForm);
        model.addAttribute("feedbackmessage", feedbackinfo);
        return "elements";
    }

    @RequestMapping(value = "/generic", method = RequestMethod.POST)
    public String generic(@ModelAttribute("contactForm") FooterContactFormModel contactForm, Model model) {
        model.addAttribute("visibility", contactForm.issendingMessageFlag());
        feedbackinfo = SendEmailService.senditsimple(contactForm);
        model.addAttribute("feedbackmessage", feedbackinfo);
        return "generic";
    }

    @RequestMapping(value = "/landing", method = RequestMethod.POST)
    public String landing(@ModelAttribute("contactForm") FooterContactFormModel contactForm, Model model) {
        model.addAttribute("visibility", contactForm.issendingMessageFlag());
        feedbackinfo = SendEmailService.senditsimple(contactForm);
        model.addAttribute("feedbackmessage", feedbackinfo);
        return "landing";
    }

}
