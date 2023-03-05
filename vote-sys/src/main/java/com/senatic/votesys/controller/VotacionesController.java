package com.senatic.votesys.controller;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.senatic.votesys.model.enums.EstadoVotacion;
import com.senatic.votesys.model.Votacion;
import com.senatic.votesys.service.IVotacionesService;

@Controller
@RequestMapping("/votaciones")
public class VotacionesController {

    @Autowired
    private IVotacionesService votacionesService;
    
    @GetMapping("/view")
    public String getVotaciones(@RequestParam(defaultValue = "0") Integer page,
                                @RequestParam(defaultValue = "5") Integer size,
                                Model model){
        Pageable paging = PageRequest.of(page, size);
        Page<Votacion> listVotaciones = votacionesService.getVotacionesPaginate(paging);
        model.addAttribute("votaciones", listVotaciones);
        return "/admin/votaciones/list";
    }

    @GetMapping("/search")
    public String searchVotacion(@RequestParam("nombre") String nombre,
                                @RequestParam("estado") String estado,
                                @RequestParam(defaultValue = "0") Integer page,
                                @RequestParam(defaultValue = "5") Integer size,
                                Model model){
        Votacion votacion = new Votacion();
        votacion.setNombre(nombre);
        votacion.setEstado(EstadoVotacion.valueOf(estado));
        ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("nombre", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase(true));
        Pageable paging = PageRequest.of(page, size);
        Example<Votacion> example = Example.of(votacion, matcher);
        Page<Votacion> listVotaciones = votacionesService.getVotacionesPaginateByExample(paging, example);
        model.addAttribute("votaciones", listVotaciones);
        return "/admin/votaciones/list";
    }

    @GetMapping("/edit/{id}")
    public String editVotacion(@PathVariable("id") Integer idVotacion, Model model, RedirectAttributes attributes){
        Optional<Votacion> optional = votacionesService.getVotacionById(idVotacion);
        if (optional.isPresent()) {
            model.addAttribute("votacion", optional.get());
            return "/admin/votaciones/add";
        } else {
            attributes.addFlashAttribute("msgDanger", "No existe la votación que intenta editar");
            return "redirect:/votaciones/view";
        }
    }

    @GetMapping("/create")
    public String createVotacion(Votacion votacion){
        return "/admin/votaciones/add";
    }

    @PostMapping("/save")
    public String saveVotacion(Votacion votacion, RedirectAttributes attributes, Model model){
        votacion.setEstado(EstadoVotacion.CREADA);
        votacionesService.addVotacion(votacion);
        attributes.addFlashAttribute("msgDone", "Votación guardada satisfactoriamente");
        return "redirect:/votaciones/view";
    }

    @PatchMapping("/disable/{id}")
    public String disableVotacion(@PathVariable(name="id", required = true) Integer idVotacion, RedirectAttributes attribute){
        Optional<Votacion> optional = votacionesService.getVotacionById(idVotacion);
        if (optional.isPresent()) {
            votacionesService.disableVotacionById(optional.get().getId());
            attribute.addFlashAttribute("msgDone", "Votación deshabilitada satisfactoriamente");
        }else {
        attribute.addFlashAttribute("msgDanger", "No existe la votación que desea deshabilitar");
        }
        return "redirect:/votaciones/view";
    }

    @PatchMapping("/enable/{id}")
    public String enableVotacion(@PathVariable(name="id", required = true) Integer idVotacion, RedirectAttributes attribute){
        Optional<Votacion> optional = votacionesService.getVotacionById(idVotacion);
        if (optional.isPresent()) {
            votacionesService.enableVotacionById(optional.get().getId());
            attribute.addFlashAttribute("msgDone", "Votación habilitada satisfactoriamente");
        }else {
        attribute.addFlashAttribute("msgDanger", "No existe la votación que desea habilitar");
        }
        return "redirect:/votaciones/view";
    }

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}

    @ModelAttribute
    public void setGenerics(Model model){
        model.addAttribute("votaciones", votacionesService.getVotaciones());
    }
}
