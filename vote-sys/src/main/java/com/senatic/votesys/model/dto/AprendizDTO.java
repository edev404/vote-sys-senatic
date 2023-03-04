package com.senatic.votesys.model.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;


@Data
@JsonPropertyOrder({"ficha", "programa", "tipoDocumento", "numeroDocumento", "nombre", "apellido", "celular", "correoElectronico", "estado"})
public class AprendizDTO {
    private String nombre;
    private String apellido;
    private String tipoDocumento;
    private String celular;
    private String correoElectronico;
    private String numeroDocumento;
    private String ficha;
    private String programa;
    private String estado;
}
