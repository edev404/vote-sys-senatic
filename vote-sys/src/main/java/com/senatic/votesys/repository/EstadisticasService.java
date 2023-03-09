package com.senatic.votesys.repository;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.senatic.votesys.model.Aprendiz;
import com.senatic.votesys.model.Candidato;
import com.senatic.votesys.model.Estadisticas;
import com.senatic.votesys.model.Votacion;
import com.senatic.votesys.model.enums.EstadoAprendiz;
import com.senatic.votesys.model.enums.EstadoCandidato;
import com.senatic.votesys.service.IAprendicesService;
import com.senatic.votesys.service.ICandidatosService;
import com.senatic.votesys.service.IEstadisticasService;
import com.senatic.votesys.service.IVotacionesService;
import com.senatic.votesys.service.IVotosService;

@Service
@Primary
public class EstadisticasService implements IEstadisticasService{

    @Autowired
    private IVotosService votosService;

    @Autowired
    private ICandidatosService candidatosService;

    @Autowired
    private IAprendicesService aprendicesService;

    @Autowired
    private IVotacionesService votacionesService;

    @Override
    public Estadisticas getEstadisticas(Votacion votacion) {

        Estadisticas estadisticas = new Estadisticas();

        estadisticas.setVotacion(votacion);
        estadisticas.setCandidatoMasVotado(null);
        estadisticas.setCandidatos(getCandidatos(votacion.getId()));
        estadisticas.setCantidadVotos(getCantidadVotos(votacion));
        estadisticas.setVotantesHabilitados(getVotantesHabilitados());
        estadisticas.setVotosPorCandidato(getVotosPorCandidato(votacion.getId()));
        estadisticas.setCandidatoMasVotado(getCandidatoMasVotado(votacion.getId()));
        estadisticas.setFechaRegistro(new Date());

        return estadisticas;
    }


    @Override
    public long getVotantesHabilitados() {
        return aprendicesService.countAprendicesByEstado(EstadoAprendiz.EN_FORMACION);
    }

    @Override
    public long getCantidadVotos(Votacion votacion) {
        return votosService.countByVotacion(votacion);
    }

    @Override
    public List<Candidato> getCandidatos(Integer idVotacion) {
        return candidatosService.getCandidatosByVotacionAndEstado(idVotacion, EstadoCandidato.HABILITADO);
    }

    @Override
    public Map<String, Long> getVotosPorCandidato(Integer idVotacion) {
        Map<String, Long> votosPorCandidato = new HashMap<String, Long>();
        getCandidatos(idVotacion).stream().forEach(candidato -> {
            //Pasar objetos completos
            Votacion votacion = votacionesService.getVotacionById(idVotacion).get();
            long cantidadVotos = votosService.getByVotacionAndCandidato(votacion, candidato).stream().count();
            votosPorCandidato.put(candidato.getAprendiz().getId(), cantidadVotos);
        });
        return votosPorCandidato;
    }

    @Override
    public Candidato getCandidatoMasVotado(Integer idVotacion) {
        //Map.Entry<String, Long> maxEntry = null;
        // getVotosPorCandidato(idVotacion).entrySet().stream().forEach(entry -> extracted(maxEntry, entry));
        // .stream().max(Comparator.comparingLong());
        String idAprendiz = Collections.max(getVotosPorCandidato(idVotacion).entrySet(), Map.Entry.comparingByValue()).getKey();
        Aprendiz aprendiz = aprendicesService.getAprendizById(idAprendiz).get();
        Candidato candidato = candidatosService.getCandidatoByAprendiz(aprendiz).get();
        return candidato;
    }

}
