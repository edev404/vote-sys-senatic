package com.senatic.votesys.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="candidatos")
public class Candidato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "idAprendiz")
    private Aprendiz aprendiz;
    @OneToOne
    @JoinColumn(name="idImagen")
    private Imagen imagen;
    @OneToOne
    @JoinColumn(name="idVotacion")
    private Votacion votacion;
    @Nullable
    private String propuestas;
    @Enumerated(EnumType.STRING)
    private EstadoCandidato estatus;
}
