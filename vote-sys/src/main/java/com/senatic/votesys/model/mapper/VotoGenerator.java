package com.senatic.votesys.model.mapper;

import java.util.Date;

import com.senatic.votesys.model.Aprendiz;
import com.senatic.votesys.model.Candidato;
import com.senatic.votesys.model.Votacion;
import com.senatic.votesys.model.Voto;

public class VotoGenerator{
    public static Voto getVotoFormatted(Integer idCandidato, String idAprendiz, Integer idVotacion){
        Candidato candidato = new Candidato();
        candidato.setId(idCandidato);
        Aprendiz aprendiz = new Aprendiz();
        aprendiz.setId(idAprendiz);
        Votacion votacion = new Votacion();
        votacion.setId(idVotacion);
        Voto voto = new Voto();
        voto.setAprendiz(aprendiz);
        voto.setCandidato(candidato);
        voto.setVotacion(votacion);
        voto.setFechaRegistro(new Date());
        voto.setValido(true);
        return voto;
    }
}
