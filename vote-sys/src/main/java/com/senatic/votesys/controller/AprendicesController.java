package com.senatic.votesys.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.senatic.votesys.model.Aprendiz;
import com.senatic.votesys.model.dto.AprendizPOJO;
import com.senatic.votesys.model.mapper.GenericMapper;
import com.senatic.votesys.service.IAprendicesService;
import com.senatic.votesys.utils.FileHandler;

@Controller
@RequestMapping("/aprendices")
public class AprendicesController {

    @Autowired
    private IAprendicesService aprendicesService;

    @Autowired
    private GenericMapper<AprendizPOJO, Aprendiz> genericMapper;

    @GetMapping("/create/form")
    public String createAprendizForm(AprendizPOJO aprendizPOJO, Model model) {
        return "admin/aprendiz/form";
    }

    @PostMapping("/create/form")
    public String createAprendiz(AprendizPOJO aprendizPOJO, RedirectAttributes redirectAtt) {
        Optional<Aprendiz> optional = aprendicesService.getAprendizById(aprendizPOJO.getNumeroDocumento());
        if (optional.isPresent()) {
            redirectAtt.addFlashAttribute("msgDanger", "Ya existe un aprendiz con el documento proporcionado");
            return "redirect:/aprendices/create/form";
        }
        Aprendiz aprendiz = genericMapper.map(aprendizPOJO);
        aprendicesService.addAprendiz(aprendiz);
        redirectAtt.addFlashAttribute("msgDone", "el aprendiz fue almacenado satisfactoriamente");
        return "redirect:/aprendices/view";
    }

    @GetMapping("/create/upload")
    public String createAprendicesByCSV() {
        return "admin/aprendiz/upload";
    }

    @PostMapping("/create/upload")
    public String saveAprendicesByCSV(@RequestParam("csvFile") MultipartFile csvFile, RedirectAttributes attributes) {
        List<AprendizPOJO> aprendicesPOJO = new ArrayList<>();
        if (FileHandler.isCsv(csvFile)) {
            aprendicesPOJO = FileHandler.csvToAprendizPOJOs(csvFile);
            if (!aprendicesPOJO.isEmpty()) {
                List<Aprendiz> aprendices = aprendicesPOJO.stream().map(aprendizDTO -> genericMapper.map(aprendizDTO))
                        .toList();
                aprendicesService.addAprendices(aprendices);
            } else {
                attributes.addFlashAttribute("msgDanger", "El csv no contiene registros");
            }
            attributes.addFlashAttribute("msgDone", "Todos los registros guardados exitosamente");
        } else {
            attributes.addFlashAttribute("msgDanger", "El unico formato soportado es csv...");
        }
        return "redirect:/aprendices/view";
    }

    @GetMapping("/view")
    public String getAprendices(@RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "6") Integer size,
            org.springframework.ui.Model model) {
        // TO DO: Desplegar lista de aprendices en la vista
        /*
         * Debe soportar paginación y ordenación por ficha
         * Debe soportar busqueda By example por ficha
         */
        Pageable paging = PageRequest.of(page, size);
        Page<Aprendiz> listAprendices = aprendicesService.getAprendicesPaginate(paging);
        model.addAttribute("aprendices", listAprendices);
        return "admin/aprendiz/list";
    }

    @GetMapping("/search")
    public String aprendicesFilter(@RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "5") Integer size,
            org.springframework.ui.Model model,
            @ModelAttribute("search") Aprendiz aprendiz) {
        Example<Aprendiz> example = Example.of(aprendiz);
        Pageable paging = PageRequest.of(page, size);
        Page<Aprendiz> listAprendices = aprendicesService.getAprendicesPaginateByExample(paging, example);
        model.addAttribute("aprendices", listAprendices);
        return "admin/aprendiz/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteAprendizById(@PathVariable("id") String id, RedirectAttributes redirectAtt) {
        Optional<Aprendiz> aprendizOpt = aprendicesService.findById(id);
        if (aprendizOpt.isPresent()) {
            aprendicesService.deleteById(id);
            redirectAtt.addFlashAttribute("msg", "borrado exitosamente");
        } else {
            redirectAtt.addFlashAttribute("msg", "no existe tal aprendiz que desea borrar");
        }
        return "redirect:/aprendices/view";
    }

    @GetMapping("/edit/{id}")
    public String editAprendizView(@PathVariable("id") String id, Model model, RedirectAttributes redirectAtt) {
        Optional<Aprendiz> aprendizOpt = aprendicesService.findById(id);
        if (aprendizOpt.isPresent()) {
            model.addAttribute("aprendiz", aprendizOpt.get());
            return "admin/aprendiz/edit";
        } else {
            redirectAtt.addFlashAttribute("msg", "no existe tal aprendiz que desea borrar");
        }
        return "redirect:/aprendices/view";
    }

    @PostMapping("/edit")
    public String editAprendiz(Aprendiz aprendiz, RedirectAttributes redirectAtt) {
        aprendicesService.addAprendiz(aprendiz);
        redirectAtt.addFlashAttribute("msgDone", "el aprendiz fue almacenado satisfactoriamente");
        return "redirect:/aprendices/view";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @ModelAttribute
    public void setGenerics(Model model){
        model.addAttribute("search", new Aprendiz());
    }
}
