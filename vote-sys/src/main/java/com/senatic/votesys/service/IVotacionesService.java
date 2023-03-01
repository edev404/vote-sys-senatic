package com.senatic.votesys.service;

import java.util.List;

import com.senatic.votesys.model.Votacion;

public interface IVotacionesService {
    List<Votacion> getVotaciones();
    void addVotacion(Votacion votacion);
    void deleteById(Long idVotacion);
}
