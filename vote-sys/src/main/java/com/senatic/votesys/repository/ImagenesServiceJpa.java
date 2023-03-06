package com.senatic.votesys.repository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.senatic.votesys.model.Imagen;
import com.senatic.votesys.repository.bd.ImagenesRepository;
import com.senatic.votesys.service.IImagenesService;

@Service
@Primary
public class ImagenesServiceJpa implements IImagenesService{

    @Autowired
    private ImagenesRepository imagenesRepository;

    @Override
    public void addImagen(Imagen imagen) {
        imagenesRepository.save(imagen);
    }

    @Override
    public Optional<Imagen> getImagenById(String idImagen) {
        return imagenesRepository.findById(idImagen);
    }
    
}
