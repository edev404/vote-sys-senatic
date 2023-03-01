package com.senatic.votesys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.senatic.votesys.model.EstadoVotacion;
import com.senatic.votesys.model.Votacion;
import com.senatic.votesys.service.IVotacionesService;

@Controller
@RequestMapping("/votaciones")
public class VotacionesController {

    @Autowired
    private IVotacionesService votacionesService;
    
    @GetMapping("/view")
    public String getVotaciones(Model model){
        return "/admin/votaciones/list";
    }

    @GetMapping("/create")
    public String createVotacion(Votacion votacion){
        return "/admin/votaciones/add";
    }

    @PostMapping("/create")
    public String saveVotacion(Votacion votacion, RedirectAttributes attributes){
        votacion.setEstado(EstadoVotacion.CREADA);
        votacionesService.addVotacion(votacion);
        attributes.addFlashAttribute("msg", "Votación creada satisfactoriamente");
        return "redirect:/votaciones/view";
    }

    @GetMapping("/delete/{id}")
    public String deleteVotacion(@PathVariable(name="id", required = true) Long idVotacion, RedirectAttributes attributes){
        votacionesService.deleteById(idVotacion);
        attributes.addFlashAttribute("msg", "Votación eliminada satisfactoriamente");
        return "redirect:/votaciones/view";
    }

    @ModelAttribute
    public void setGenerics(Model model){
        model.addAttribute("listVotaciones", votacionesService.getVotaciones());
    }
}
