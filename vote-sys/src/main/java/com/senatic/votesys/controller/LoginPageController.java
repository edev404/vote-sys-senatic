package com.senatic.votesys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
public class LoginPageController {
    
    @GetMapping
    public String login(@RequestParam(name="error", defaultValue = "false") Boolean err){
        return "login";
    }
}
