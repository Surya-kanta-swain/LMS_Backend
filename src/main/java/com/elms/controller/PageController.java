package com.elms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
    @GetMapping("/")
    public String register(Model model){
        model.addAttribute("hello",1);
        return "register";
    }

}
