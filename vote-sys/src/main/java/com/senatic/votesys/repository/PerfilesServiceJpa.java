package com.senatic.votesys.repository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.senatic.votesys.model.Perfil;
import com.senatic.votesys.model.Usuario;
import com.senatic.votesys.repository.bd.PerfilesRepository;
import com.senatic.votesys.service.IPerfilesService;

@Service
@Primary
public class PerfilesServiceJpa implements IPerfilesService{

    @Autowired
    private PerfilesRepository perfilesRepository;

    @Override
    public Optional<Perfil> getPerfil(String perfil) {
        return perfilesRepository.findByPerfil(perfil);
    }

    @Override
    public Boolean isThisPerfil(Usuario usuario, String perfil) {
        return usuario.getPerfiles().stream().filter(p -> p.equals(perfilesRepository.findByPerfil(perfil).get())).findFirst().isPresent();
    }

    
    
}
