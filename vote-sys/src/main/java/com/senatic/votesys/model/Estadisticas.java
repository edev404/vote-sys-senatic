package com.senatic.votesys.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Estadisticas {
    
    private Votacion votacion;
    private Long cantidadVotos;
    private Long votantesHabilitados;
    private Long votantes;
    private List<Candidato> candidatos;
    private Map<String, Long> votosPorCandidato;
    private Candidato candidatoMasVotado;
    private LocalDateTime fechaRegistro;

}
