package com.senatic.votesys.service;

import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.senatic.votesys.model.Candidato;

public interface ICandidatosService {
    Page<Candidato> getCandidatosPaginate(Pageable page);
    Page<Candidato> getCandidatosPaginateByExample(Pageable page, Example<Candidato> example);
    void deleteCandidatoById(Long idCandidato);
    Optional<Candidato> getCandidatoById(Long idCandidato);
    void deleteCandidato(Candidato candidato);
    void addCandidato(Candidato candidato);
    void disableCandidatoById(Long idCandidato);
    void enableCandidatoById(Long idCandidato);
}
