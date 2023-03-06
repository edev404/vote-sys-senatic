package com.senatic.votesys.model.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CandidatoPOJO {
    
    private String documento;
    private MultipartFile imagen;
    private Integer idVotacion;
    private String propuestas;
}
