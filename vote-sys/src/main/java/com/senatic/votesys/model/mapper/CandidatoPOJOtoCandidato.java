package com.senatic.votesys.model.mapper;



import java.io.IOException;
import java.util.Base64;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.senatic.votesys.model.Aprendiz;
import com.senatic.votesys.model.Candidato;
import com.senatic.votesys.model.Imagen;
import com.senatic.votesys.model.enums.EstadoCandidato;
import com.senatic.votesys.service.IAprendicesService;
import com.senatic.votesys.service.IImagenesService;
import com.senatic.votesys.service.IVotacionesService;
import com.senatic.votesys.model.Votacion;
import com.senatic.votesys.model.dto.CandidatoPOJO;

@Service
@Primary
public class CandidatoPOJOtoCandidato implements GenericMapper<CandidatoPOJO, Candidato>{

    @Autowired
    private IAprendicesService aprendicesService;

    @Autowired
    private IVotacionesService votacionesService;

    @Autowired
    private IImagenesService imagenesService;

    @Override
    public Candidato map(CandidatoPOJO pojo) {
        Candidato candidato = new Candidato();

        
        //Aprendiz field
        Aprendiz aprendiz = aprendicesService.getAprendizById(pojo.getDocumento()).get();
        candidato.setAprendiz(aprendiz);

        //Propuestas field
        candidato.setPropuestas(pojo.getPropuestas());

        //Estatus field
        candidato.setEstado(EstadoCandidato.HABILITADO);
        
        //Votacion field
        //Tomar votacion completa
        Votacion votacion = votacionesService.getVotacionById(pojo.getIdVotacion()).get();
        candidato.setVotacion(votacion);

        //Image object and field
        Imagen imagen = new Imagen();
        MultipartFile image = pojo.getImagen();
        try {
            // Blob imageBlob = new SerialBlob(image.getBytes());
            // imagen.setImage(imageBlob);
            imagen.setId(pojo.getDocumento().trim());
            imagen.setImage(Base64.getEncoder().encodeToString(image.getBytes()));
        } catch ( IOException e) {
            e.printStackTrace();
        }
        imagenesService.addImagen(imagen);

        Optional<Imagen> optional = imagenesService.getImagenById(pojo.getDocumento());
        if (optional.isPresent()) {
            candidato.setImagen(optional.get());
        }        
        return candidato;
    }
    
}
