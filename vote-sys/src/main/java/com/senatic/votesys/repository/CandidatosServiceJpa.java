package com.senatic.votesys.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.senatic.votesys.model.Aprendiz;
import com.senatic.votesys.model.Candidato;
import com.senatic.votesys.model.Votacion;
import com.senatic.votesys.model.enums.EstadoCandidato;
import com.senatic.votesys.repository.bd.CandidatosRepository;
import com.senatic.votesys.service.ICandidatosService;
import com.senatic.votesys.service.IVotacionesService;

@Service
@Primary
public class CandidatosServiceJpa implements ICandidatosService{
    

    @Autowired
    private CandidatosRepository candidatosRepository;

    @Autowired
    private IVotacionesService votacionesService;
    
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

    @Override
    public List<Candidato> getCandidatosByVotacionAndEstado(Integer idVotacion, EstadoCandidato estado) {
        Votacion votacion = votacionesService.getVotacionById(idVotacion).get();
        return candidatosRepository.findByVotacionAndEstado(votacion, estado);
    }

    @Override
    public Optional<Candidato> getCandidatoByAprendiz(Aprendiz aprendiz) {
        return candidatosRepository.findByAprendiz(aprendiz);
    }
   
    
}
