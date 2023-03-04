package com.senatic.votesys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.senatic.votesys.exception.CsvParsingException;
import com.senatic.votesys.model.Aprendiz;
import com.senatic.votesys.model.dto.AprendizDTO;
import com.senatic.votesys.service.IAprendicesService;
import com.senatic.votesys.utils.FileHandler;


@Controller
@RequestMapping("/aprendices")
public class AprendicesController {
    
	@Autowired
	private IAprendicesService aprendizService;
	
    @PostMapping("/create/form")
    public String createAprendiz(AprendizDTO aprendiz, RedirectAttributes redirectAtt){
        /*
        TO DO: 
        Recibir un aprendiz del form y guardarlo mediante el servicio correspondiente
        Redirigir a la lista de aprendices
        Enviar mensaje de confirmación
         */
    	if(!aprendizService.addAprendiz(null)) {
    		redirectAtt.addFlashAttribute("msg", "el aprendiz ya existe");
    	}else {
    		redirectAtt.addFlashAttribute("msg", "el aprendiz fue almacenado satisfactoriamente");
    	}
        return "redirect:/aprendices/view";
    }

    @GetMapping("/create/upload")
    public String createAprendicesByCSV(){
        /*
        TO DO:
        Retornar vista para subir archivo
         */
        return "";
    }

    @PostMapping("/create/upload")
    public String saveAprendicesByCSV(@RequestParam("csvFile") MultipartFile csvFile, RedirectAttributes ra){
        /* 
        TO DO: 
        Recibir un MultipartFile y Obtener lista de aprendices
        Crear 1 usuario por cada aprendiz - username & password = documento identidad
        Agregar aprendices y usuarios
        Redirigir a vista de aprendices
        Enviar mensaje de confirmación
        */
    	try {
			FileHandler.csvToList(csvFile);
			ra.addFlashAttribute("msg", "todos los registros guardados exitosamente");
		} catch (CsvParsingException e) {
			ra.addFlashAttribute("msg", "hubo errores al guardar los registros del documento CSV");
		}
        return "redirect:/aprendices/view";
    }

    @GetMapping("/view")
    public String getAprendices(@RequestParam(defaultValue = "0") Integer page,
            					@RequestParam(defaultValue = "5") Integer size,
            					org.springframework.ui.Model model){
        //TO DO: Desplegar lista de aprendices en la vista
        /*
         Debe soportar paginación y ordenación por ficha
         Debe soportar busqueda By example por ficha
         */
    	Pageable paging = PageRequest.of(page, size);
        Page<Aprendiz> listAprendices = aprendizService.getAprendicesPaginate(paging);
        model.addAttribute("aprendices", listAprendices);
        return "admin/add-aprendiz/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteAprendizById(@PathVariable("id") String id, RedirectAttributes redirectAtt){
        /*
        TO DO: 
        Eliminar al aprendiz por id y redirigir a la vista de la lista de aprendices
        Enviar mensaje de confirmación
         */
    	aprendizService.deleteById(id);
    	redirectAtt.addFlashAttribute("msg", "borrado exitosamente");
        return "redirect:/aprendices/view";
    }

    @GetMapping("/update/{id}")
    public String updateAprendizView(@PathVariable("id") String id, org.springframework.ui.Model model){
        /*
        TO DO:
        Retornar el formulario de aprendiz con todos los datos correspondientes al aprendiz del id
         */
    	model.addAttribute("aprendiz", aprendizService.findById(id).get());
        return "admin/add-aprendiz/form";
    }

    @PostMapping("/update/")
    public String updateAprendiz(AprendizDTO aprendiz, RedirectAttributes redirectAtt){
        /*
        TO DO:
        Recibir un aprendiz como parametro y almacenarlo
        Redirigir a la vista de aprendices
        Enviar mensaje de confirmación
         */
    	aprendizService.updateAprendiz(aprendiz);
        return "redirect:/aprendices/view";
    }
    
    @InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
}
