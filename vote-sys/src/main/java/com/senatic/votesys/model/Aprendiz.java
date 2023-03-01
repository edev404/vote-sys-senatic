package com.senatic.votesys.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
@Table(name="Aprendices")
public class Aprendiz {
    
    @Id
    private String id;
    @OneToOne
    @JoinColumn(name="idUsuario")
    private Usuario usuario;
    private String tipoDocumento;
    private String fichaPrograma;
}
