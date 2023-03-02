package com.senatic.votesys.repository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.senatic.votesys.model.Voto;
import com.senatic.votesys.repository.bd.VotosRepository;
import com.senatic.votesys.service.IVotosService;

@Service
@Primary
public class VotosServiceJpa implements IVotosService{

    @Autowired
    private VotosRepository votosRepository;

    @Override
    public Boolean hasAlreadyVote(String idAprendiz, Long idVotacion) {
        Optional<Voto> voto = votosRepository.findByAprendizAndVotacion(idAprendiz, idVotacion);
        if (voto.isPresent()) {
            return true;
        }
        return false;
    }   
}
