package com.senatic.votesys.repository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.senatic.votesys.model.Aprendiz;
import com.senatic.votesys.model.Votacion;
import com.senatic.votesys.model.dto.AprendizDTO;
import com.senatic.votesys.repository.bd.AprendicesRepository;
import com.senatic.votesys.service.IAprendicesService;

@Service
@Primary
public class AprendicesServiceJpa implements IAprendicesService{

	@Autowired
	private AprendicesRepository aprendicesRepo;
	
	@Override
	public Page<Aprendiz> getAprendicesPaginate(Pageable paging) {
		return aprendicesRepo.findAll(paging);
	}

	@Override
	public boolean addAprendiz(Aprendiz aprendiz) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
    public void deleteById(String idAprendiz) {
        aprendicesRepo.deleteById(idAprendiz);
    }
    
	@Override
	public Optional<Aprendiz> findById(String id) {
		return aprendicesRepo.findById(id);
	}
	
	@Override
	public boolean updateAprendiz(AprendizDTO aprendizDTO) {
		Aprendiz aprendiz = aprendicesRepo.getReferenceById(aprendizDTO.getId());
		aprendiz.setId(aprendizDTO.getId());
		aprendiz.setFichaPrograma(aprendizDTO.getFichaPrograma());
		aprendiz.setTipoDocumento(aprendizDTO.getTipoDocumento);
	}
	
	
}
