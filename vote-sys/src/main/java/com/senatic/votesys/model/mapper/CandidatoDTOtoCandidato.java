package com.senatic.votesys.model.mapper;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialBlob;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.senatic.votesys.model.Aprendiz;
import com.senatic.votesys.model.Candidato;
import com.senatic.votesys.model.Imagen;
import com.senatic.votesys.model.Votacion;
import com.senatic.votesys.model.dto.CandidatoDTO;

@Service
@Primary
public class CandidatoDTOtoCandidato implements GenericMapper<CandidatoDTO, Candidato>{

    @Override
    public Candidato map(CandidatoDTO dto) {
        Candidato candidato = new Candidato();

        //Aprendiz field
        Aprendiz aprendiz = new Aprendiz();
        aprendiz.setId(dto.getDocumento());
        candidato.setAprendiz(aprendiz);

        //Propuestas field
        candidato.setPropuestas(dto.getPropuestas());

        //Estatus field
        candidato.setEstatus(EstadoCandidato.HABILITADO);
        
        //Votacion field
        Votacion votacion = new Votacion();
        votacion.setId(dto.getIdVotacion());

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
