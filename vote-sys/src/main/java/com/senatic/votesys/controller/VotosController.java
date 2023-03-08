package com.senatic.votesys.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.senatic.votesys.model.Candidato;
import com.senatic.votesys.model.Usuario;
import com.senatic.votesys.model.mapper.VotoGenerator;
import com.senatic.votesys.service.ICandidatosService;
import com.senatic.votesys.service.IUsuariosService;
import com.senatic.votesys.service.IVotosService;

@Controller
@RequestMapping("/votos")
public class VotosController {

    @Autowired
    private IVotosService votosService;

    @Autowired
    private IUsuariosService usuariosService;

    @Autowired
    private ICandidatosService candidatosService;
    
    @GetMapping("/vote-by/{idCandidato}")
    public String saveVoto(@PathVariable("idCandidato") Integer idCandidato,
                            RedirectAttributes attributes){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            Optional<Usuario> optional = usuariosService.findByUsername(authentication.getName());
            if(optional.isPresent()){
                Usuario usuario = optional.get();
                Candidato candidato = candidatosService.getCandidatoById(idCandidato).get();

                String idAprendiz = usuario.getUsername();
                Integer idVotacion = candidato.getVotacion().getId();
                //Chequeando
                if (votosService.hasAlreadyVote(idAprendiz, idVotacion)) {
                    attributes.addFlashAttribute("msgDanger", "No puedes votar de nuevo");
                    return "redirect:/home/aprendiz";
                }else {
                    votosService.registerVote(VotoGenerator.getVotoFormatted(idCandidato, idAprendiz, idVotacion));
                    attributes.addFlashAttribute("msgDone", "Voto registrado. Gracias por participar");
                    return "redirect:/home/aprendiz";
                }
            }
        } else {
            return "redirect:/doLogout";
        }
        return "redirect:/home/aprendiz";
    }
}
