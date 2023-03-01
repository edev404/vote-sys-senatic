package com.senatic.votesys.repository.bd;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.senatic.votesys.model.Candidato;

@Repository
public interface CandidatosRepository extends JpaRepository<Candidato, Long>{
    
}
