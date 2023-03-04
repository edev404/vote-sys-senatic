package com.senatic.votesys.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.senatic.votesys.model.Aprendiz;
import com.senatic.votesys.model.Candidato;
import com.senatic.votesys.model.dto.CandidatoDTO;
import com.senatic.votesys.model.mapper.GenericMapper;
import com.senatic.votesys.service.IAprendicesService;
import com.senatic.votesys.service.ICandidatosService;

@Controller
@RequestMapping("/candidatos")
public class CandidatosController {
    
    @Autowired
    private ICandidatosService candidatosService;

    @Autowired
    private IAprendicesService aprendicesService;

    @Autowired
    private GenericMapper<CandidatoDTO, Candidato> genericMapper;

    @GetMapping("/view")
    public String getCandidatos(@RequestParam(defaultValue = "0") Integer page,
                                @RequestParam(defaultValue = "5") Integer size,
                                Model model) {
        Pageable paging = PageRequest.of(page, size);
        Page<Candidato> listCandidatos = candidatosService.getCandidatosPaginate(paging);
        model.addAttribute("candidatos", listCandidatos);
        return "/admin/candidatos/list";
    }

    @GetMapping("/search")
    public String searchCandidato(@ModelAttribute("search") Candidato candidato,
                                @RequestParam(defaultValue = "0") Integer page,
                                @RequestParam(defaultValue = "5") Integer size,
                                Model model){
        ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("idCategoria", ExampleMatcher.GenericPropertyMatchers.contains());
        Example<Candidato> example = Example.of(candidato, matcher);
        Pageable paging = PageRequest.of(page, size);
        Page<Candidato> listCandidatos = candidatosService.getCandidatosPaginateByExample(paging, example);
        model.addAttribute("candidatos", listCandidatos);
        return "/admin/candidatos/list";
    }

    @PatchMapping("/enable/{id}")
    public String enableCandidatoById(@PathVariable("id") Long idCandidato){
        Optional<Candidato> optional = candidatosService.getCandidatoById(idCandidato);
        if (optional.isPresent()) {
            candidatosService.enableCandidatoById(optional.get().getId());
            attribute.addFlashAttribute("msg", "Candidato habilitado satisfactoriamente");
        } else {
            attribute.addFlashAttribute("msg", "No existe el candidato que desea habilitar");
        }
        return "redirect:/candidatos/view";
    }

    @PatchMapping("/disable/{id}")
    public String disableCandidatoById(@PathVariable("id") Long idCandidato){
        Optional<Candidato> optional = candidatosService.getCandidatoById(idCandidato);
        if (optional.isPresent()) {
            candidatosService.disableCandidatoById(optional.get().getId());
            attribute.addFlashAttribute("msg", "Candidato inhabilitado satisfactoriamente");
        } else {
            attribute.addFlashAttribute("msg", "No existe el candidato que desea inhabilitar");
        }
        return "redirect:/candidatos/view";
    }


    @GetMapping("/delete/{id}")
    public String deleteCandidatoById(@PathVariable("id") Long idCandidato, RedirectAttributes attribute){
        Optional<Candidato> optional = candidatosService.getCandidatoById(idCandidato);
        if (optional.isPresent()) {
            candidatosService.deleteCandidato(optional.get());
            attribute.addFlashAttribute("msg", "Candidato eliminado satisfactoriamente");
        } else {
            attribute.addFlashAttribute("msg", "No existe el candidato que desea eliminar");
        }
        return "redirect:/candidatos/view";
    }

    @GetMapping("/create")
    public String createCandidatoForm(Candidato candidato){
        return "/admin/candidatos/add";
    }

    @GetMapping("/edit/{id}")
    public String editCandidato(@PathVariable("id") Long idCandidato,  Model model, RedirectAttributes attributes){
        Optional<Candidato> optional = candidatosService.getCandidatoById(idCandidato);
        if (optional.isPresent()) {
            model.addAttribute("candidato", optional.get());
            return "/admin/candidatos/add";
        } else {
            attributes.addFlashAttribute("msg", "No existe el candidato que intenta editar");
            return "redirect:/votaciones/view";
        }
    }

    @PostMapping("/save")
    public String saveCandidato(CandidatoDTO candidato, RedirectAttributes attributes, Model model){
        Optional<Aprendiz> optional = aprendicesService.findById(candidato.getDocumento());
        if (optional.isEmpty()) {
            attributes.addFlashAttribute("msg", "El documento proporcionado no corresponde a ningun aprendiz");
            return "redirect:/candidatos/create";
        }
        Candidato candidatoMapped = genericMapper.map(candidato);
        candidatosService.addCandidato(candidatoMapped);
        attributes.addFlashAttribute("msg", "Candidato guardado satisfactoriamente");
        return "redirect:/candidatos/view";
    }

    @ModelAttribute
    public void setGenerics(Model model){
        model.addAttribute("search", new Candidato());
    }
}
