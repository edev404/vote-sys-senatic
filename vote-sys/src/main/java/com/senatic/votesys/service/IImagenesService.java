package com.senatic.votesys.service;

import java.util.Optional;

import com.senatic.votesys.model.Imagen;

public interface IImagenesService {
    void addImagen(Imagen imagen);   
    Optional<Imagen> getImagenById(String idImagen);
    void updateBlobById(String idImagen, String image);
}
