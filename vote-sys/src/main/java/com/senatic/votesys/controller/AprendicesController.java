package com.senatic.votesys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
        Recibir un MultipartFile y Obtener lista de aprendices
        Crear 1 usuario por cada aprendiz - username & password = documento identidad
        Agregar aprendices y usuarios
        Redirigir a vista de aprendices
        Enviar mensaje de confirmación
        */
        return "redirect:/aprendices/view";
    }

    @GetMapping("/view")
    public String viewAprendices(){
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
