package com.senatic.votesys.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import com.senatic.votesys.model.Votacion;
import com.senatic.votesys.repository.bd.VotacionesRepository;
import com.senatic.votesys.service.IVotacionesService;

import jakarta.transaction.Transactional;

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
    public void deleteById(Integer idVotacion) {
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


    @Override
    public void deleteVotacion(Votacion votacion) {
        votacionesJpa.delete(votacion);
    }

    @Transactional
    @Modifying
    @Override
    public void disableVotacionById(Integer idVotacion) {
        votacionesJpa.disableVotacionById(idVotacion);
    }

    @Transactional
    @Modifying
    @Override
    public void enableVotacionById(Integer idVotacion) {
        votacionesJpa.enableVotacionById(idVotacion);
    }

    @Override
    public Optional<Votacion> getVotacionById(Integer idVotacion) {
        return votacionesJpa.findById(idVotacion);
    }

    @Override
    public List<Votacion> getVotacionesByEstado() {
        return votacionesJpa.findAll()
        .stream()
        .filter(votacion -> votacion.getEstado().toString().equalsIgnoreCase("ABIERTA"))
        .toList();
};
}

