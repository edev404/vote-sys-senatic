package com.senatic.votesys.repository.bd;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.senatic.votesys.model.Aprendiz;
import com.senatic.votesys.model.enums.EstadoAprendiz;

@Repository
public interface AprendicesRepository extends JpaRepository<Aprendiz, String>{
    Integer countByEstado(EstadoAprendiz estado);
}
