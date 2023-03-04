package com.senatic.votesys.model.mapper;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.senatic.votesys.model.Aprendiz;
import com.senatic.votesys.model.Perfil;
import com.senatic.votesys.model.Usuario;
import com.senatic.votesys.model.dto.AprendizDTO;
import com.senatic.votesys.model.enums.EstadoAprendiz;
import com.senatic.votesys.service.IPerfilesService;

@Service
@Primary
public class AprendizDTOtoAprendiz implements GenericMapper<AprendizDTO, Aprendiz>{

    @Autowired
    private IPerfilesService perfilesService;
    @Autowired
	private PasswordEncoder passwordEncoder;

    @Override
    public Aprendiz map(AprendizDTO dto) {

        Aprendiz aprendiz = new Aprendiz();

        //Generic fields
        aprendiz.setId(dto.getNumeroDocumento());
        aprendiz.setNombre(dto.getNombre());
        aprendiz.setApellido(dto.getApellido());
        aprendiz.setCelular(dto.getCelular());
        aprendiz.setFicha(dto.getFicha());
        aprendiz.setPrograma(dto.getPrograma());
        aprendiz.setTipoDocumento(dto.getTipoDocumento());
        aprendiz.setEstado(dto.getEstado().equalsIgnoreCase("EN FORMACION") ? EstadoAprendiz.EN_FORMACION : EstadoAprendiz.CANCELADO);

        //Aprendiz Usuario fields
        Usuario usuario = new Usuario();
        usuario.setUsername(dto.getNumeroDocumento());
        usuario.setPassword(passwordEncoder.encode(dto.getNumeroDocumento()));
        usuario.setFechaRegistro(new Date());

        //Usuario's perfil fields
        Perfil perfil = perfilesService.getPerfil("APRENDIZ").get();
        usuario.setPerfiles(List.of(perfil));
        aprendiz.setUsuario(usuario);

        return aprendiz;
    }
    
}
