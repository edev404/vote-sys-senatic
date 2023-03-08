package com.senatic.votesys.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.senatic.votesys.model.Aprendiz;
import com.senatic.votesys.model.dto.AprendizPOJO;
import com.senatic.votesys.model.enums.EstadoAprendiz;
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
	public void addAprendiz(Aprendiz aprendiz) {
		aprendicesRepository.save(aprendiz);
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
	public boolean updateAprendiz(AprendizPOJO aprendizDTO) {
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
	
	@Override
	public Page<Aprendiz> getAprendicesPaginateByExample(Pageable paging, Example<Aprendiz> example) {
		return aprendicesRepository.findAll(example, paging);
	}

	@Override
	public void addAprendices(List<Aprendiz> aprendices) {
		aprendicesRepository.saveAll(aprendices);
	}

	@Override
	public Integer countAprendicesByEstado(EstadoAprendiz estado) {
		return aprendicesRepository.countByEstado(estado);
	}
	
	
	
}
