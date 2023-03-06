package com.senatic.votesys.repository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.senatic.votesys.model.Usuario;
import com.senatic.votesys.repository.bd.UsuariosRepository;
import com.senatic.votesys.service.IUsuariosService;

@Service
@Primary
public class UsuariosServiceJpa implements IUsuariosService{

    @Autowired
    private UsuariosRepository usuariosRepository;

    @Override
    public Optional<Usuario> findByUsername(String username) {
        return usuariosRepository.findByUsername(username);
    }

    @Override
    public void addUsuario(Usuario usuario) {
        usuariosRepository.save(usuario);
    }

   
}
