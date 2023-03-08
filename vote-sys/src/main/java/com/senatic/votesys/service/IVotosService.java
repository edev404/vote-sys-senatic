package com.senatic.votesys.service;

import java.util.List;

import com.senatic.votesys.model.Voto;

public interface IVotosService {
    Boolean hasAlreadyVote(String idAprendiz, Integer idVotacion);
    Integer getCandidadVotos(Integer idVotacion);
    List<Voto> getByVotacion(Integer idVotacion);
    Integer countByVotacion(Integer idVotacion);
    List<Voto> getByVotacionAndCandidato(Integer idVotacion, Integer idCandidato);
    void registerVote(Voto voto);
}
