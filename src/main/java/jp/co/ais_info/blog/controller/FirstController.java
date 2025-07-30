package jp.co.ais_info.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller//Controller declared
public class FirstController {
    @GetMapping("/hi") //PathLocation
    public String niceToMeetYou(Model model){
        model.addAttribute("username", "Kim");
        return "greetings"; //Viewer
    }

    @GetMapping("/bye")
    public String goodBye(Model model){
        model.addAttribute("nickname", "Park");
        return "goodbye";
    }
}