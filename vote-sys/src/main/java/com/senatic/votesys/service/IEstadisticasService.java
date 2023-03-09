package com.senatic.votesys.service;

import java.util.List;
import java.util.Map;

import com.senatic.votesys.model.Candidato;
import com.senatic.votesys.model.Estadisticas;
import com.senatic.votesys.model.Votacion;

public interface IEstadisticasService {
    
    Estadisticas getEstadisticas(Votacion votacion);
    long getVotantesHabilitados();
    long getCantidadVotos(Votacion votacion);
    List<Candidato> getCandidatos(Integer idVotacion);
    Map<String, Long> getVotosPorCandidato(Integer idVotacion);
    Candidato getCandidatoMasVotado(Integer idVotacion);

}
