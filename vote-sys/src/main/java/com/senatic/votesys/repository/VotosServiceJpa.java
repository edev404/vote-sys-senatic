package com.senatic.votesys.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.senatic.votesys.model.Aprendiz;
import com.senatic.votesys.model.Candidato;
import com.senatic.votesys.model.Votacion;
import com.senatic.votesys.model.Voto;
import com.senatic.votesys.repository.bd.VotosRepository;
import com.senatic.votesys.service.IAprendicesService;
import com.senatic.votesys.service.IVotacionesService;
import com.senatic.votesys.service.IVotosService;

@Service
@Primary
public class VotosServiceJpa implements IVotosService{

    @Autowired
    private VotosRepository votosRepository;

    @Autowired
    private IAprendicesService aprendicesService;

    @Autowired
    private IVotacionesService votacionesService;

    @Override
    public Boolean hasAlreadyVote(String idAprendiz, Integer idVotacion) {
        Aprendiz aprendiz = aprendicesService.getAprendizById(idAprendiz).get();
        Votacion votacion = votacionesService.getVotacionById(idVotacion).get();
        Optional<Voto> voto = votosRepository.findByAprendizAndVotacion(aprendiz, votacion);
        if (voto.isPresent()) {
            return true;
        }
        return false;
    }

    @Override
    public List<Voto> getByVotacion(Votacion votacion) {
        return votosRepository.findByVotacion(votacion);
    }

    @Override
    public long countByVotacion(Votacion votacion) {
        return votosRepository.findByVotacion(votacion).stream().count();
    }

    @Override
    public List<Voto> getByVotacionAndCandidato(Votacion votacion, Candidato candidato) {
        return votosRepository.findByVotacionAndCandidato(votacion, candidato);
    }   

    @Override
    public void registerVote(Voto voto){
        votosRepository.save(voto);
    }
}
