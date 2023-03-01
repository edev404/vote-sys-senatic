package com.senatic.votesys.repository.bd;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.senatic.votesys.model.Votacion;

@Repository
public interface VotacionesJpa extends JpaRepository<Votacion, Long>{
    
}
