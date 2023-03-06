package com.senatic.votesys.service;

import java.util.Optional;

import com.senatic.votesys.model.Usuario;

public interface IUsuariosService {

    Optional<Usuario> findByUsername(String username);
    void addUsuario(Usuario usuario);
}
