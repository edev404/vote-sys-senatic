package com.senatic.votesys.model.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"ficha", "programa", "tipoDocumento", "numeroDocumento", "nombre", "apellido", "celular", "correoElectronico", "estado"})
public class AprendizPOJO {
    private String ficha;
    private String programa;
    private String tipoDocumento;
    private String numeroDocumento;
    private String nombre;
    private String apellido;
    private String celular;
    private String correoElectronico;
    private String estado;
}
