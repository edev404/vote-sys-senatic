package com.senatic.votesys.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.senatic.votesys.model.Votacion;
import com.senatic.votesys.model.enums.EstadoVotacion;

public interface IVotacionesService {
    List<Votacion> getVotaciones();
    void addVotacion(Votacion votacion);
    void deleteById(Integer idVotacion);
    void deleteVotacion(Votacion votacion);
    Page<Votacion> getVotacionesPaginate(Pageable paging);
    Page<Votacion> getVotacionesPaginateByExample(Pageable paging, Example<Votacion> example);
    Optional<Votacion> getVotacionById(Integer idVotacion);
    void disableVotacionById(Integer idVotacion);
    void enableVotacionById(Integer idVotacion);
    List<Votacion> getVotacionesByEstado(EstadoVotacion estado);
}
