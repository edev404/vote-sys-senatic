package com.senatic.votesys.service;

import java.util.List;

import com.senatic.votesys.model.Candidato;
import com.senatic.votesys.model.Votacion;
import com.senatic.votesys.model.Voto;

public interface IVotosService {
    Boolean hasAlreadyVote(String idAprendiz, Integer idVotacion);
    List<Voto> getByVotacion(Votacion votacion);
    long countByVotacion(Votacion votacion);
    List<Voto> getByVotacionAndCandidato(Votacion votacion, Candidato candidato);
    void registerVote(Voto voto);
}
