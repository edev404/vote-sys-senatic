package com.senatic.votesys.repository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.senatic.votesys.model.Aprendiz;
import com.senatic.votesys.model.dto.AprendizDTO;
import com.senatic.votesys.repository.bd.AprendicesRepository;
import com.senatic.votesys.service.IAprendicesService;

@Service
@Primary
public class AprendicesServiceJpa implements IAprendicesService{

	@Autowired
	private AprendicesRepository aprendicesRepository;
	
	@Override
	public Page<Aprendiz> getAprendicesPaginate(Pageable paging) {
		return aprendicesRepository.findAll(paging);
	}

	@Override
	public boolean addAprendiz(Aprendiz aprendiz) {
		return true;
	}
	
	@Override
    public void deleteById(String idAprendiz) {
        aprendicesRepository.deleteById(idAprendiz);
    }
    
	@Override
	public Optional<Aprendiz> findById(String id) {
		return aprendicesRepository.findById(id);
	}
	
	@Override
	public boolean updateAprendiz(AprendizDTO aprendizDTO) {
		//CORREGIR
		/*
		TO DO:
		Cuando actualizamos, recibamos directamente un objeto aprendiz, por cuestiones del usuario.
		No puede ser modificado el documento de identidad
		 */
		Aprendiz aprendiz = aprendicesRepository.getReferenceById(aprendizDTO.getNumeroDocumento());
		aprendiz.setId(aprendizDTO.getNumeroDocumento());
		aprendiz.setFicha(aprendizDTO.getFicha());
		aprendiz.setTipoDocumento(aprendizDTO.getTipoDocumento());
		return true;
	}

	@Override
	public Optional<Aprendiz> getAprendizById(String idAprendiz) {
		return aprendicesRepository.findById(idAprendiz);
	}
	
	
}
