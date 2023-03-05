package com.senatic.votesys.model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.JoinColumn;
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
@Table(name="usuarios", indexes = @Index(name="usuarios_unique", columnList = "username", unique = true))
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 10)
    private String username;
    @Column(length = 10)
    private String password;
    @ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(
				name="UsuarioPerfil",
				joinColumns= @JoinColumn(name="idUsuario"),
				inverseJoinColumns=@JoinColumn(name="idPerfil")
				)
	private List<Perfil> perfiles;
    private Date fechaRegistro;
}
