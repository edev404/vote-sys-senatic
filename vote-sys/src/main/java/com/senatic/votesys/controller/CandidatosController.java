package com.senatic.votesys.controller;

import java.io.IOException;
import java.util.Base64;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.senatic.votesys.model.Aprendiz;
import com.senatic.votesys.model.Candidato;
import com.senatic.votesys.model.Imagen;
import com.senatic.votesys.model.dto.CandidatoPOJO;
import com.senatic.votesys.model.enums.EstadoCandidato;
import com.senatic.votesys.model.mapper.GenericMapper;
import com.senatic.votesys.service.IAprendicesService;
import com.senatic.votesys.service.ICandidatosService;
import com.senatic.votesys.service.IImagenesService;
import com.senatic.votesys.service.IVotacionesService;

@Controller
@RequestMapping("/candidatos")
public class CandidatosController {

    @Autowired
    private ICandidatosService candidatosService;

    @Autowired
    private IAprendicesService aprendicesService;

    @Autowired
    private IVotacionesService votacionesService;

    @Autowired
    private IImagenesService imagenesService;

    @Autowired
    private GenericMapper<CandidatoPOJO, Candidato> genericMapper;

    @GetMapping("/view")
    public String getCandidatos(@RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "6") Integer size,
            Model model) {
        Pageable paging = PageRequest.of(page, size);
        Page<Candidato> listCandidatos = candidatosService.getCandidatosPaginate(paging);
        model.addAttribute("candidatos", listCandidatos);
        return "admin/candidatos/list";
    }

    @GetMapping("/search")
    public String searchCandidato(@ModelAttribute("search") Candidato candidato,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "5") Integer size,
            Model model) {
        ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("votacion.id",
                ExampleMatcher.GenericPropertyMatchers.contains());
        Example<Candidato> example = Example.of(candidato, matcher);
        Pageable paging = PageRequest.of(page, size);
        Page<Candidato> listCandidatos = candidatosService.getCandidatosPaginateByExample(paging, example);
        model.addAttribute("candidatos", listCandidatos);
        return "admin/candidatos/list";
    }

    @PatchMapping("/enable/{id}")
    public String enableCandidatoById(@PathVariable("id") Integer idCandidato, RedirectAttributes attributes) {
        Optional<Candidato> optional = candidatosService.getCandidatoById(idCandidato);
        if (optional.isPresent()) {
            candidatosService.enableCandidatoById(optional.get().getId());
            attributes.addFlashAttribute("msgDone", "Candidato habilitado satisfactoriamente");
        } else {
            attributes.addFlashAttribute("msgDanger", "No existe el candidato que desea habilitar");
        }
        return "redirect:/candidatos/view";
    }

    @PatchMapping("/disable/{id}")
    public String disableCandidatoById(@PathVariable("id") Integer idCandidato, RedirectAttributes attributes) {
        Optional<Candidato> optional = candidatosService.getCandidatoById(idCandidato);
        if (optional.isPresent()) {
            candidatosService.disableCandidatoById(optional.get().getId());
            attributes.addFlashAttribute("msgDone", "Candidato inhabilitado satisfactoriamente");
        } else {
            attributes.addFlashAttribute("msgDanger", "No existe el candidato que desea inhabilitar");
        }
        return "redirect:/candidatos/view";
    }

    @GetMapping("/delete/{id}")
    public String deleteCandidatoById(@PathVariable("id") Integer idCandidato, RedirectAttributes attribute) {
        Optional<Candidato> optional = candidatosService.getCandidatoById(idCandidato);
        if (optional.isPresent()) {
            candidatosService.deleteCandidato(optional.get());
            attribute.addFlashAttribute("msgDone", "Candidato eliminado satisfactoriamente");
        } else {
            attribute.addFlashAttribute("msgDanger", "No existe el candidato que desea eliminar");
        }
        return "redirect:/candidatos/view";
    }

    @GetMapping("/update/{id}")
    public String updateCandidatoForm(@PathVariable("id") Integer idCandidato, Model model,
            RedirectAttributes attributes) {
        Optional<Candidato> optional = candidatosService.getCandidatoById(idCandidato);
        if (optional.isPresent()) {
            model.addAttribute("candidato", optional.get());
            return "admin/candidatos/edit";
        } else {
            attributes.addFlashAttribute("msgDanger", "No existe el candidato que intenta editar");
            return "redirect:/candidatos/view";
        }
    }

    @PostMapping("/update")
    public String updateCandidato(@RequestParam(name = "archivoImagen", required = false) MultipartFile file,
            Candidato candidato, RedirectAttributes attributes, Model model) {
        
        //@RequestParam(name = "documento", required = true) String idAprendiz
        candidato.setAprendiz(aprendicesService.findById(candidato.getAprendiz().getId()).get());
        
        if (!file.isEmpty()) {
            Imagen imagen = new Imagen();
            Optional<Imagen> optionalImagen = imagenesService.getImagenById(candidato.getAprendiz().getId());
            MultipartFile image = file;
            try {
                imagen.setId(candidato.getAprendiz().getId());
                imagen.setImage(Base64.getEncoder().encodeToString(image.getBytes()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            //Chequeamos si existe una imagen con el mismo id
            if (optionalImagen.isPresent()) {
                imagenesService.updateBlobById(imagen.getId(), imagen.getImage());
            } else {
                imagenesService.addImagen(imagen);
            }
    
            //Despues de actualizar o guardar
            Optional<Imagen> optional = imagenesService.getImagenById(candidato.getAprendiz().getId());
            if (optional.isPresent()) {
                candidato.setImagen(optional.get());
            }
        }
        candidato.setEstado(EstadoCandidato.HABILITADO);
        candidatosService.addCandidato(candidato);
        attributes.addFlashAttribute("msgDone", "Candidato actualizado satisfactoriamente");
        return "redirect:/candidatos/view";
    }

    @GetMapping("/create")
    public String createCandidatoForm(CandidatoPOJO candidatoPOJO) {
        return "admin/candidatos/add";
    }

    @PostMapping("/create")
    public String createCandidato(CandidatoPOJO candidatoPOJO, RedirectAttributes attributes, Model model) {
        Optional<Aprendiz> optional = aprendicesService.findById(candidatoPOJO.getDocumento());
        if (optional.isEmpty()) {
            attributes.addFlashAttribute("msgDanger", "El documento proporcionado no corresponde a ningun aprendiz");
            return "redirect:/candidatos/create";
        }
        Candidato candidato = genericMapper.map(candidatoPOJO);
        candidatosService.addCandidato(candidato);
        attributes.addFlashAttribute("msgDone", "Candidato guardado satisfactoriamente");
        return "redirect:/candidatos/view";
    }

    // Convierte el null los string vacíos
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @ModelAttribute
    public void setGenerics(Model model) {
        model.addAttribute("search", new Candidato());
        model.addAttribute("votaciones", votacionesService.getVotaciones());
    }
}
