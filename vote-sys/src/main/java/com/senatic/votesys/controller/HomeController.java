package com.senatic.votesys.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.senatic.votesys.model.Usuario;
import com.senatic.votesys.service.IPerfilesService;
import com.senatic.votesys.service.IUsuariosService;

@Controller
public class HomeController {
    
    @Autowired
    private IUsuariosService usuariosService;

    @Autowired
    private IPerfilesService perfilesService;

    @GetMapping("/")
    public String redirectByRole(Authentication auth){
        Optional<Usuario> optional = usuariosService.findByUsername(auth.getUsername());
        if(optional.isPresent()){
            Usuario usuario = optional.get();
            if (perfilesService.isThisPerfil(usuario, "APRENDIZ")) {
                return "redirect:/aprendices/home";
            } else if (perfilesService.isThisPerfil(usuario, "ADMINISTRADOR")){
                return "redirect:/administrador/home";
            }
        }
        return "redirect:/logout";
    }

}
