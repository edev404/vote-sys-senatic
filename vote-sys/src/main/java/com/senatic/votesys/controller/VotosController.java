package com.senatic.votesys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.senatic.votesys.service.IVotosService;

@Controller
@RequestMapping("/votos")
public class VotosController {

    @Autowired
    private IVotosService votosService;
    
    @PatchMapping("/{idAprendiz}/vote-by")
    public String saveVoto(@RequestParam("candidato") Integer idCandidato,
                            @PathVariable("idAprendiz") String idAprendiz,
                            RedirectAttributes attributes){

        if (votosService.hasAlreadyVote(idAprendiz, idCandidato)) {
            attributes.addAttribute("msgDanger", "No puedes votar de nuevo");
        }else {
            attributes.addAttribute("msgDone", "Voto registrado. Gracias por participar");
        }
        return "redirect:/aprendices/home";
    }

}
