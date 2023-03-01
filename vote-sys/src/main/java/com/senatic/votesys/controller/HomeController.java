package com.senatic.votesys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    
    @GetMapping("/")
    public String redirectByRole(){
        /*
        Redirigir a la vista correspodiente basandose en el rol del usuario autenticado
         */
        return "";
    }

}
