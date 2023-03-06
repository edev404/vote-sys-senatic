package com.senatic.votesys.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.senatic.votesys.model.Aprendiz;
import com.senatic.votesys.model.dto.AprendizPOJO;

public interface IAprendicesService {
	Page<Aprendiz> getAprendicesPaginate(Pageable paging);
    void addAprendiz(Aprendiz aprendiz);
	void deleteById(String idAprendiz);
	Optional<Aprendiz> findById(String id);
	boolean updateAprendiz(AprendizPOJO aprendizPOJO);
	Optional<Aprendiz> getAprendizById(String idAprendiz);
	void addAprendices(List<Aprendiz> aprendices);
	Page<Aprendiz> getAprendicesPaginateByExample(Pageable paging, Example<Aprendiz> example);
}
