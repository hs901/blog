package jp.co.ais_info.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller//Controller declared
public class SecondController {
    @GetMapping("/quotes")
    public String randomQuote(Model model){
        String[] quotes = {
                "Be yourself; everyone else is already taken." +" -Oscar Wilde-",
                "You know you're in love when you can't fall asleep because reality is finally better than your dreams." +" -Dr. Seuss-",
                "Be who you are and say what you feel, because those who mind don't matter, and those who matter don't mind." +" -Bernard M. Baruch-",
                "You only live once, but if you do it right, once is enough." +" -Mae West-",
                "Be the change that you wish to see in the world." +" -Mahatma Gandhi-"
        };
        int randInt = (int) (Math.random() * quotes.length);
        model.addAttribute("randomQuote", quotes[randInt]);
        return "quote";
    }
}