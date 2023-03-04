package com.senatic.votesys.repository;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.senatic.votesys.model.Candidato;
import com.senatic.votesys.repository.bd.CandidatosRepository;
import com.senatic.votesys.service.ICandidatosService;

@Service
@Primary
public class CandidatosServiceJpa implements ICandidatosService{
    

    @Autowired
    private CandidatosRepository candidatosRepository;
    
    @Override
    public Page<Candidato> getCandidatosPaginate(Pageable page){
        return candidatosRepository.findAll(page);
    }

    @Override
    public Page<Candidato> getCandidatosPaginateByExample(Pageable page, Example<Candidato> example){
        return candidatosRepository.findAll(example, page);
    }


    @Override
    public Optional<Candidato> getCandidatoById(Integer idCandidato) {
        return candidatosRepository.findById(idCandidato);
    }

    @Override
    public void deleteCandidato(Candidato candidato) {
        candidatosRepository.delete(candidato);
    }

    @Override
    public void addCandidato(Candidato candidato) {
        candidatosRepository.save(candidato);
    }

    @Override
    public void disableCandidatoById(Integer idCandidato){
        candidatosRepository.disableCandidatoById(idCandidato);
    }

    @Override
    public void enableCandidatoById(Integer idCandidato){
        candidatosRepository.enableCandidatoById(idCandidato);
    }

    @Override
    public void deleteCandidatoById(Integer idCandidato) {
        candidatosRepository.deleteById(idCandidato);
    }
   
    
}
