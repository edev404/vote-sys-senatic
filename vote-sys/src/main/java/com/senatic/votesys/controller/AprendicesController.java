package com.senatic.votesys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.senatic.votesys.exception.CsvParsingException;
import com.senatic.votesys.utils.FileHandler;

import ch.qos.logback.core.model.Model;

@Controller
@RequestMapping("/aprendices")
public class AprendicesController {
    
    @GetMapping("/create/form")
    public String createAprendiz(){
        /*
        TO DO: 
        Recibir un aprendiz del form y guardarlo mediante el servicio correspondiente
        Redirigir a la lista de aprendices
        Enviar mensaje de confirmación
         */
        return "";
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
    public String viewAprendices(Model model){
        //TO DO: Desplegar lista de aprendices en la vista
        /*
         Debe soportar paginación y ordenación por ficha
         Debe soportar busqueda By example por ficha
         */
    	
        return "";
    }

    @GetMapping("/delete/{id}")
    public String deleteAprendizById(){
        /*
        TO DO: 
        Eliminar al aprendiz por id y redirigir a la vista de la lista de aprendices
        Enviar mensaje de confirmación
         */
        return "redirect:/aprendices/view";
    }

    @GetMapping("/update/{id}")
    public String updateAprendizView(){
        /*
        TO DO:
        Retornar el formulario de aprendiz con todos los datos correspondientes al aprendiz del id
         */
        return "";
    }

    @PostMapping("/update/")
    public String updateAprendiz(){
        /*
        TO DO:
        Recibir un aprendiz como parametro y almacenarlo
        Redirigir a la vista de aprendices
        Enviar mensaje de confirmación
         */
        return "redirect:/aprendices/view";
    }
}
