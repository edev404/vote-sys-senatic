package com.senatic.votesys.repository.bd;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.senatic.votesys.model.Aprendiz;
import com.senatic.votesys.model.Candidato;
import com.senatic.votesys.model.Votacion;
import com.senatic.votesys.model.Voto;

@Repository
public interface VotosRepository extends JpaRepository<Voto, Integer>{
    
    @Query(value="", nativeQuery = true)
    Optional<Candidato> findTopByVotacion(@Param("idVotacion") Integer idVotacion);

    Optional<Voto> findByAprendizAndVotacion(Aprendiz aprendiz, Votacion votacion);
    List<Voto> findByVotacion(Votacion votacion);
    List<Voto> findByVotacionAndCandidato(Votacion votacion, Candidato candidato);
}
