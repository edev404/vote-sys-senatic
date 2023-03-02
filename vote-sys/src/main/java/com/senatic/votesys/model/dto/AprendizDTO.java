package com.senatic.votesys.model.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;


@Data
@JsonPropertyOrder({"id", "tipoDocumento", "fichaPrograma"})
public class AprendizDTO {
    private String id;
    private String tipoDocumento;
    private String fichaPrograma;
}
