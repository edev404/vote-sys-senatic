package com.senatic.votesys.model;

import java.util.Date;


import com.senatic.votesys.model.enums.EstadoVotacion;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString @EqualsAndHashCode
@Entity
@Table(name="votaciones")
public class Votacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 100)
    private String nombre;
    @Column(length = 250)
    private String descripcion;
    @Enumerated(EnumType.STRING)
    private EstadoVotacion estado;
    private Date fechaTerminacion;
    
}
