package com.senatic.votesys.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.senatic.votesys.model.Aprendiz;
import com.senatic.votesys.model.dto.AprendizDTO;

public interface IAprendicesService {
	Page<Aprendiz> getAprendicesPaginate(Pageable paging);
    boolean addAprendiz(Aprendiz aprendiz);
	void deleteById(String idAprendiz);
	Optional<Aprendiz> findById(String id);
	boolean updateAprendiz(AprendizDTO aprendizDTO);
}
