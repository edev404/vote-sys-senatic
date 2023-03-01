package com.senatic.votesys.repository.bd;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.senatic.votesys.model.Usuario;

@Repository
public interface UsuariosRepository extends JpaRepository<Usuario, Long>{
    
}
