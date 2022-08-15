package br.com.andersillva.gameflixusuarioapi.domain.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Table(name="usuario")
@Data
public class Usuario {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_usuario", nullable=false)
	private Long id;

	@Column(name="nm_usuario", length=60, nullable=false)
	@NotBlank
	private String nome;

	@Column(name="nr_cpf", length=11, nullable=false)
	@NotBlank
	private String cpf;

	@Column(name="dt_nascimento", nullable=false)
	@NotNull
	private LocalDate dataNascimento;

	@Column(name="ds_email", length=30, nullable=false)
	@NotBlank
	private String email;

	@Column(name="ds_senha", length=100, nullable=false)
	@NotBlank
	private String senha;

}
