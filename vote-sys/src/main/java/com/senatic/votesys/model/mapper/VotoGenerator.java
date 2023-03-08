package com.senatic.votesys.model.mapper;

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
