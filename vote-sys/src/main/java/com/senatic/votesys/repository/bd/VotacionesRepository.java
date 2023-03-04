package com.senatic.votesys.repository.bd;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.senatic.votesys.model.Votacion;

@Repository
public interface VotacionesRepository extends JpaRepository<Votacion, Integer>{
    
    @Query(value="UPDATE votaciones c SET c.estatus = 'CERRADA' WHERE c.id= :idVotacion" , nativeQuery = true)
    void disableVotacionById(Integer idVotacion);
    
    @Query(value="UPDATE votaciones c SET c.estatus = 'ABIERTA' WHERE c.id= :idVotacion" , nativeQuery = true)
    void enableVotacionById(Integer idVotacion);

}
