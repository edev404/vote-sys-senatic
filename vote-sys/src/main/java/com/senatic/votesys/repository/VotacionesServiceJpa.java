package com.senatic.votesys.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.senatic.votesys.model.Votacion;
import com.senatic.votesys.repository.bd.VotacionesRepository;
import com.senatic.votesys.service.IVotacionesService;

@Service
@Primary
public class VotacionesServiceJpa implements IVotacionesService{

    @Autowired
    private VotacionesRepository votacionesJpa;

    @Override
    public List<Votacion> getVotaciones() {
        return votacionesJpa.findAll();
    }

    @Override
    public void addVotacion(Votacion votacion) {
        votacionesJpa.save(votacion);
    }

    @Override
    public void deleteById(Long idVotacion) {
        votacionesJpa.deleteById(idVotacion);
    }

    @Override
    public Page<Votacion> getVotacionesPaginate(Pageable paging) {
        return votacionesJpa.findAll(paging);
    }

    @Override
    public Page<Votacion> getVotacionesPaginateByExample(Pageable paging, Example<Votacion> example) {
        return votacionesJpa.findAll(example, paging);
    }
    
}
