package com.senatic.votesys.repository.bd;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.senatic.votesys.model.Candidato;
import com.senatic.votesys.model.Votacion;
import com.senatic.votesys.model.enums.EstadoCandidato;

@Repository
public interface CandidatosRepository extends JpaRepository<Candidato, Integer>{
    
    @Query(value="UPDATE candidatos c SET c.estatus = 'INHABILITADO' WHERE c.id= :idCandidato" , nativeQuery = true)
    void disableCandidatoById(Integer idCandidato);
    
    @Query(value="UPDATE candidatos c SET c.estatus = 'HABILITADO' WHERE c.id= :idCandidato" , nativeQuery = true)
    void enableCandidatoById(Integer idCandidato);

    List<Candidato> findByVotacionAndEstado(Votacion votacion, EstadoCandidato estado);
}
