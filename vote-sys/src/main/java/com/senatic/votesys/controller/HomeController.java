package com.senatic.votesys.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.senatic.votesys.model.Candidato;
import com.senatic.votesys.model.Usuario;
import com.senatic.votesys.model.Votacion;
import com.senatic.votesys.model.enums.EstadoCandidato;
import com.senatic.votesys.model.enums.EstadoVotacion;
import com.senatic.votesys.service.ICandidatosService;
import com.senatic.votesys.service.IPerfilesService;
import com.senatic.votesys.service.IUsuariosService;
import com.senatic.votesys.service.IVotacionesService;
import com.senatic.votesys.service.IVotosService;

@Controller
public class HomeController {

    @Autowired
    private IUsuariosService usuariosService;

    @Autowired
    private IPerfilesService perfilesService;

    @Autowired
    private IVotacionesService votacionesService;

    @Autowired
    private ICandidatosService candidatosService;

    @Autowired
    private IVotosService votosService;

    @GetMapping("/home")
    public String redirectByRole(RedirectAttributes attributes) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            Optional<Usuario> optional = usuariosService.findByUsername(authentication.getName());
            if (optional.isPresent()) {
                Usuario usuario = optional.get();
                // Si es aprendiz
                if (perfilesService.isThisPerfil(usuario, "APRENDIZ")) {
                    String idAprendiz = usuario.getUsername();
                    Integer idVotacion = 16;
                    // Chequeando si votó
                    if (votosService.hasAlreadyVote(idAprendiz, idVotacion)) {
                        attributes.addFlashAttribute("msgDanger", "No puede acceder al sistema. Ya registró su voto");
                        return "redirect:/login"; // Logout si ya votó
                    } else {
                        return "redirect:/home/aprendiz/search/current"; // Home si no votó
                    }
                } else if (perfilesService.isThisPerfil(usuario, "ADMINISTRADOR")) {
                    return "redirect:/aprendices/view";
                }
            } else {
                return "redirect:/login";
            }
        }
        return "redirect:/login";
    }

    @GetMapping("/home/aprendiz")
    public String homeAprendiz(Model model) {
        model.addAttribute("msg", "No existen candidatos disponibles");

        return "aprendiz/home";
    }

    @GetMapping("/home/aprendiz/search")
    public String homeAprendizSearch(@ModelAttribute("search") Votacion votacion, Model model,
            RedirectAttributes attributes) {
        Optional<Votacion> optional = votacionesService.getVotacionById(votacion.getId());
        if (optional.isPresent()) {
            List<Candidato> candidatosRs = candidatosService.getCandidatosByVotacionAndEstado(votacion.getId(),
                    EstadoCandidato.HABILITADO);
            List<Candidato> candidatos = candidatosRs.stream()
                    .filter(c -> c.getVotacion().getEstado()
                            .equals(EstadoVotacion.ABIERTA))
                    .toList();
            model.addAttribute("candidatos", candidatos);
            return "aprendiz/home";
        } else {
            attributes.addFlashAttribute("msgDanger", "No existe la votación que intenta consultar");
            return "redirect:/home/aprendiz";
        }
    }

    @GetMapping("/home/aprendiz/search/current")
    public String currentVotationDeploy(Model model, RedirectAttributes attributes) {
        // 16
        Optional<Votacion> optional = votacionesService.getVotacionById(16);
        if (optional.isPresent()) {
            if(optional.get().getEstado().equals(EstadoVotacion.ABIERTA)){
                List<Candidato> candidatosRs = candidatosService.getCandidatosByVotacionAndEstado(16,
                        EstadoCandidato.HABILITADO);
                List<Candidato> candidatos = candidatosRs.stream()
                        .filter(c -> c.getVotacion().getEstado()
                                .equals(EstadoVotacion.ABIERTA))
                        .toList();
                model.addAttribute("candidatos", candidatos);
                return "aprendiz/home";
            } else {
                attributes.addFlashAttribute("msgDanger", "La votación se encuentra cerrada");
                return "redirect:/login";
            }
        } else {
            attributes.addFlashAttribute("msgDanger", "No existe la votación que intenta consultar");
            return "redirect:/login";
        }

    }

    @GetMapping("/home/aprendiz/candidato/details/{id}")
    public String homeAprendizSearch(@PathVariable("id") Integer idCandidato, Model model,
            RedirectAttributes attributes) {
        Optional<Candidato> optional = candidatosService.getCandidatoById(idCandidato);
        if (optional.isPresent()) {
            model.addAttribute("candidato", optional.get());
            return "aprendiz/detail";
        } else {
            attributes.addFlashAttribute("msgDanger", "No existe el candidato que intenta consultar");
            return "redirect:/home/aprendiz";
        }
    }

    @GetMapping("/home/administrador")
    public String homeAdministrador() {
        return "admin/home";
    }

    @ModelAttribute
    public void setGenerics(Model model) {
        model.addAttribute("search", new Votacion());
        model.addAttribute("votaciones", votacionesService.getVotacionesByEstado(EstadoVotacion.ABIERTA));
    }

}
