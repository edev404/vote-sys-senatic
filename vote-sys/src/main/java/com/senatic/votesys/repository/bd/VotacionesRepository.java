package com.senatic.votesys.repository.bd;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.senatic.votesys.model.Votacion;
import com.senatic.votesys.model.enums.EstadoVotacion;

@Repository
public interface VotacionesRepository extends JpaRepository<Votacion, Integer>{
    
    @Modifying
    @Query(value="UPDATE votaciones c SET c.estado = 'CERRADA' WHERE c.id= :idVotacion" , nativeQuery = true)
    void disableVotacionById(Integer idVotacion);
    
    @Modifying
    @Query(value="UPDATE votaciones c SET c.estado = 'ABIERTA' WHERE c.id= :idVotacion" , nativeQuery = true)
    void enableVotacionById(Integer idVotacion);

}
