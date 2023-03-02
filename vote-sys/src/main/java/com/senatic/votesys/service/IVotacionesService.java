package com.senatic.votesys.service;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.senatic.votesys.model.Votacion;

public interface IVotacionesService {
    List<Votacion> getVotaciones();
    void addVotacion(Votacion votacion);
    void deleteById(Long idVotacion);
    Page<Votacion> getVotacionesPaginate(Pageable paging);
    Page<Votacion> getVotacionesPaginateByExample(Pageable paging, Example<Votacion> example);
}
