package com.creditworthiness.creditworthiness.Controllers;

import com.creditworthiness.creditworthiness.Models.CreditEvaluationForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class CreditWorthinessController {

    @RequestMapping(value = "/credito", method = RequestMethod.GET)
    public String homeGet(Model model) {
        model.addAttribute("creditEvaluationForm", new CreditEvaluationForm());
        return "credito";
    }

    @RequestMapping(value = "/credito", method = RequestMethod.POST)
    public String homePost(@ModelAttribute("creditEvaluationForm") @Valid CreditEvaluationForm form,
                           BindingResult result,
                           Model model) {
        if (result.hasErrors()) {
            model.addAttribute("error", "Wypelnij poprawnie formularz");
        }
        boolean canGetCredit = false;
        double maxCreditValue = 0;

        double minTime = form.getLiabilities();
        String info = "Popraw formularz";
        String detailsInfo = "";
        if (form.getIncome() > 0 && form.getAmount() > 0 && form.getTime() > 0) {
            try {
                canGetCredit = CreditWorthinessService.hasCreditworthiness(form.getIncome(), form.getAmount(), form.getTime(), form.getLiabilities());
            } catch (NullPointerException e) {
                e.getMessage();
            }
            maxCreditValue = CreditWorthinessService.maxCreditValuation(form.getIncome(), form.getAmount(), form.getTime(), form.getLiabilities());
            minTime = CreditWorthinessService.minTime(form.getIncome(), form.getAmount(), form.getTime(), form.getLiabilities());
            if (canGetCredit)
                info = "Gratulacje, posiadasz zdolnosc kredytowa";
            else
                info = "Nie masz zdolnosci";

            if (canGetCredit) {
                info = "Gratulacje, posiadasz zdolnosc kredytowa";
                detailsInfo = "Twoja maksymalna zdolność kredytowa to: " + (int) maxCreditValue + "zł";
            } else {
                info = "Nie masz zdolnosci";
                if (minTime <= 0) {
                    detailsInfo = "";
                } else {
                    detailsInfo = "Przedłuż okres kredytowania do :" + (int) minTime + " miesięcy";
                }
            }
        }
        model.addAttribute("cangetcredit", canGetCredit);
        model.addAttribute("maxcreditvalue", maxCreditValue);
        model.addAttribute("minTime", minTime);
        model.addAttribute("income", form.getIncome());
        model.addAttribute("amount", form.getAmount());
        model.addAttribute("liabilities", form.getLiabilities());
        model.addAttribute("time", form.getTime());
        model.addAttribute("info", info);
        model.addAttribute("detailsInfo", detailsInfo);

        System.out.println(model);

        return "credito";
    }


    @RequestMapping(value = "/nic", method = RequestMethod.GET)
    public String indexGet(Model model) {
        return "index";
    }

    @RequestMapping(value = "/nic", method = RequestMethod.POST)
    public String indexPost(@RequestParam("income") double income,
                            @RequestParam("credit") double credit,
                            @RequestParam("liabilities") double liabilities,
                            @RequestParam("time") int time,
                            Model model) {
        String info = "Formularz wypełniony z błędami";
        double maxCreditValue = 0;
        boolean canGetCredit = false;
        int minTime = (int) liabilities;
        if (income > 0) {
            canGetCredit = CreditWorthinessService.hasCreditworthiness(income, credit, time, liabilities);
            maxCreditValue = CreditWorthinessService.maxCreditValuation(income, credit, time, liabilities);
            minTime = CreditWorthinessService.minTime(income, credit, time, liabilities);
            if (canGetCredit)
                info = "Gratulacje, posiadasz zdolnosc kredytowa";
            else
                info = "Nie masz zdolnosci";
        }


        System.out.println(maxCreditValue);
        model.addAttribute("cangetcredit", canGetCredit);
        model.addAttribute("maxcreditvalue", maxCreditValue);
        model.addAttribute("minTime", minTime);
        model.addAttribute("income", income);
        model.addAttribute("amount", credit);
        model.addAttribute("liabilities", liabilities);
        model.addAttribute("time", time);
        model.addAttribute("info", info);
        return "index";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String creditoGet(Model model) {
        model.addAttribute("creditEvaluationForm", new CreditEvaluationForm());
        return "home";
    }
}
