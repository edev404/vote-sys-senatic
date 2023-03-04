package com.senatic.votesys.repository.bd;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.senatic.votesys.model.Candidato;

@Repository
public interface CandidatosRepository extends JpaRepository<Candidato, Long>{
    
    @Query(value="UPDATE candidatos c SET c.estatus = 'INHABILITADO' WHERE c.id= :idCandidato" , nativeQuery = true)
    void disableCandidatoById(Long idCandidato);
    
    @Query(value="UPDATE candidatos c SET c.estatus = 'HABILITADO' WHERE c.id= :idCandidato" , nativeQuery = true)
    void enableCandidatoById(Long idCandidato);
}
