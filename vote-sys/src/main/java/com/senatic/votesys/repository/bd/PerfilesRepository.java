package com.senatic.votesys.repository.bd;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.senatic.votesys.model.Perfil;

public interface PerfilesRepository extends JpaRepository<Perfil, Integer>{
    Optional<Perfil> findByPerfil(String perfil);
}
