package com.senatic.votesys.repository.bd;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.senatic.votesys.model.Usuario;

@Repository
public interface UsuariosRepository extends JpaRepository<Usuario, Integer>{
    Optional<Usuario> findByUsername(String username);
}
