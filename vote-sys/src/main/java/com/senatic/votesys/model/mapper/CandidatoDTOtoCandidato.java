package com.senatic.votesys.model.mapper;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialBlob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.senatic.votesys.model.Aprendiz;
import com.senatic.votesys.model.Candidato;
import com.senatic.votesys.model.enums.EstadoCandidato;
import com.senatic.votesys.service.IAprendicesService;
import com.senatic.votesys.service.IVotacionesService;
import com.senatic.votesys.model.Imagen;
import com.senatic.votesys.model.Votacion;
import com.senatic.votesys.model.dto.CandidatoDTO;

@Service
@Primary
public class CandidatoDTOtoCandidato implements GenericMapper<CandidatoDTO, Candidato>{

    @Autowired
    private IAprendicesService aprendicesService;

    @Autowired
    private IVotacionesService votacionesService;

    @Override
    public Candidato map(CandidatoDTO dto) {
        Candidato candidato = new Candidato();

        
        //Aprendiz field
        Aprendiz aprendiz = aprendicesService.getAprendizById(dto.getDocumento()).get();
        candidato.setAprendiz(aprendiz);

        //Propuestas field
        candidato.setPropuestas(dto.getPropuestas());

        //Estatus field
        candidato.setEstado(EstadoCandidato.HABILITADO);
        
        //Votacion field
        //Tomar votacion completa
        Votacion votacion = votacionesService.getVotacionById(dto.getIdVotacion()).get();
        candidato.setVotacion(votacion);

        //Image object and field
        Imagen imagen = new Imagen();
        MultipartFile image = dto.getImagen();
        try {
            Blob imageBlob = new SerialBlob(image.getBytes());
            imagen.setImage(imageBlob);
            candidato.setImagen(imagen);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

        return candidato;
    }
    
}
