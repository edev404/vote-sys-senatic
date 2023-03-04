package com.senatic.votesys.service;

import java.util.Optional;

import com.senatic.votesys.model.Perfil;
import com.senatic.votesys.model.Usuario;

public interface IPerfilesService {
    Optional<Perfil> getPerfil(String perfil);
    Boolean isThisPerfil(Usuario usuario, String perfil);
}
