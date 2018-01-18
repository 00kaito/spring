package com.coverter.app.converter.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TextCounterController {

    @RequestMapping(value = "/words-counter", method = RequestMethod.GET)

    public String wordsCounterGet(Model model){
        return "wordscounter";
    }
    @RequestMapping(value = "/words-counter", method = RequestMethod.POST)
    public String wordsCounterPost(@RequestParam("texttocount") String text, Model model){
        int numOfCharacters = TextCounterService.countCharacters(text);
        int numOfWords = TextCounterService.countWords(text);
        model.addAttribute("text", text);
        model.addAttribute("characterscount", numOfCharacters);
        model.addAttribute("wordscount", numOfWords);

        return "wordscounter";
    }
}
