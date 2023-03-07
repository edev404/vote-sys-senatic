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
import com.senatic.votesys.model.dto.AprendizPOJO;
import com.senatic.votesys.model.enums.EstadoAprendiz;
import com.senatic.votesys.service.IPerfilesService;
import com.senatic.votesys.service.IUsuariosService;

@Service
@Primary
public class AprendizPOJOtoAprendiz implements GenericMapper<AprendizPOJO, Aprendiz>{

    @Autowired
    private IPerfilesService perfilesService;

    @Autowired
    private IUsuariosService usuariosService;

    @Autowired
	private PasswordEncoder passwordEncoder;

    @Override
    public Aprendiz map(AprendizPOJO pojo) {

        Aprendiz aprendiz = new Aprendiz();

        //Generic fields
        aprendiz.setId(pojo.getNumeroDocumento());
        aprendiz.setNombre(pojo.getNombre());
        aprendiz.setApellido(pojo.getApellido());
        aprendiz.setCelular(pojo.getCelular());
        aprendiz.setFicha(pojo.getFicha());
        aprendiz.setPrograma(pojo.getPrograma());
        aprendiz.setTipoDocumento(pojo.getTipoDocumento());
        aprendiz.setEstado(pojo.getEstado().equalsIgnoreCase("EN FORMACION") ? EstadoAprendiz.EN_FORMACION : EstadoAprendiz.CANCELADO);
        aprendiz.setCorreoElectronico(pojo.getCorreoElectronico());

        //Aprendiz Usuario fields
        Usuario usuario = new Usuario();
        usuario.setUsername(pojo.getNumeroDocumento());
        usuario.setPassword(passwordEncoder.encode(pojo.getNumeroDocumento()));
        usuario.setEstatus(true);
        usuario.setFechaRegistro(new Date());

        usuariosService.addUsuario(usuario);

        //Usuario's perfil fields
        Perfil perfil = perfilesService.getPerfil("APRENDIZ").get();
        usuario.setPerfiles(List.of(perfil));
        aprendiz.setUsuario(usuario);

        return aprendiz;
    }
    
}
