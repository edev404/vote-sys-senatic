package com.senatic.votesys.model.mapper;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.senatic.votesys.model.Aprendiz;
import com.senatic.votesys.model.Candidato;
import com.senatic.votesys.model.enums.EstadoCandidato;
import com.senatic.votesys.service.IAprendicesService;
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
        // Imagen imagen = new Imagen();
        // MultipartFile image = pojo.getImagen();
        // try {
        //     Blob imageBlob = new SerialBlob(image.getBytes());
        //     imagen.setImage(imageBlob);
        //     candidato.setImagen(imagen);
        // } catch (SQLException | IOException e) {
        //     e.printStackTrace();
        // }

        return candidato;
    }
    
}
