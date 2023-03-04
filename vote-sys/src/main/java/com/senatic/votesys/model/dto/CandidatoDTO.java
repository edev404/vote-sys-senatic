package com.senatic.votesys.model.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CandidatoDTO {
    
    private String documento;
    private MultipartFile imagen;
    private Long idVotacion;
    private String propuestas;
}
