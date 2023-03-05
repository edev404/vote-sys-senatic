package com.senatic.votesys.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString @EqualsAndHashCode
@Entity
@Table(name="votos", indexes = @Index(name="votos_unique", columnList = "idVotacion, idAprendiz", unique = true))
public class Voto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name="idCandidato")
    private Candidato candidato;
    @ManyToOne
    @JoinColumn(name="idAprendiz")
    private Aprendiz aprendiz;
    @ManyToOne
    @JoinColumn(name="idVotacion")
    private Votacion votacion;
    private Date fechaRegistro;
    private Boolean valido;
}
