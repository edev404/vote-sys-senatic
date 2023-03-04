package com.senatic.votesys.model;

import com.senatic.votesys.model.enums.EstadoAprendiz;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="aprendices", indexes = @Index(columnList = "id, correoElectronico, celular", unique = true))
public class Aprendiz {
    
    @Id
    private String id;
    @OneToOne
    private String nombre;
    private String apellido;
    private String tipoDocumento;
    @JoinColumn(name="idUsuario")
    private Usuario usuario;
    private String celular;
    private String correoElectronico;
    private String ficha;
    private String programa;
    @Enumerated(EnumType.STRING)
    private EstadoAprendiz estado;
}
