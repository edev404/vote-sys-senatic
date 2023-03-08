package com.senatic.votesys.repository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.senatic.votesys.model.Candidato;
import com.senatic.votesys.model.Estadisticas;
import com.senatic.votesys.model.Votacion;
import com.senatic.votesys.model.enums.EstadoAprendiz;
import com.senatic.votesys.model.enums.EstadoCandidato;
import com.senatic.votesys.service.IAprendicesService;
import com.senatic.votesys.service.ICandidatosService;
import com.senatic.votesys.service.IEstadisticasService;
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

    @Override
    public Estadisticas getEstadisticas(Votacion votacion) {

        Estadisticas estadisticas = new Estadisticas();

        estadisticas.setVotacion(votacion);
        estadisticas.setCandidatoMasVotado(null);
        estadisticas.setCandidatos(null);
        estadisticas.setVotantes(null);
        estadisticas.setVotantesHabilitados(null);
        estadisticas.setVotosPorCandidato(null);
        estadisticas.setVotosPorCandidato(null);
        estadisticas.setFechaRegistro(LocalDateTime.now());

        return estadisticas;
    }

    @Override
    public long getCantidadVotosRegistrados(Integer idVotacion) {
        return votosService.getCandidadVotos(idVotacion);
    }

    @Override
    public long getVotantesHabilitados() {
        return aprendicesService.countAprendicesByEstado(EstadoAprendiz.EN_FORMACION);
    }

    @Override
    public long getCantidadVotos(Integer idVotacion) {
        return votosService.getCandidadVotos(idVotacion);
    }

    @Override
    public List<Candidato> getCandidatos(Integer idVotacion) {
        return candidatosService.getCandidatosByVotacionAndEstado(idVotacion, EstadoCandidato.HABILITADO);
    }

    @Override
    public Map<String, Long> getVotosPorCandidato(Integer idVotacion) {
        Map<String, Long> votosPorCandidato = new HashMap<String, Long>();
        getCandidatos(idVotacion).stream().forEach(candidato -> {
            long cantidadVotos = votosService.getByVotacionAndCandidato(idVotacion, candidato.getId()).stream().count();
            votosPorCandidato.put(candidato.getAprendiz().getId(), cantidadVotos);
        });
        return votosPorCandidato;
    }

    @Override
    public Candidato getCandidatoMasVotado(Integer idVotacion) {
        Map.Entry<String, Long> maxEntry = null;
        getVotosPorCandidato(idVotacion).entrySet().stream().forEach(entry -> extracted(maxEntry, entry));
        return null;
    }

    private Entry<String, Long> extracted(Map.Entry<String, Long> maxEntry, Entry<String, Long> entry) {
        if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0){
            maxEntry = entry;
        }
        return maxEntry;
    }

}
