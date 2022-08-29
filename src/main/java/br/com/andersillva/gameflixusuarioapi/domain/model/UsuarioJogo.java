package br.com.andersillva.gameflixusuarioapi.domain.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.andersillva.gameflixusuarioapi.domain.model.domaintype.FormaInclusao;
import lombok.Data;

@Entity
@Table(name="usuario_jogo")
@Data
public class UsuarioJogo {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_usuario_jogo", nullable=false)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_usuario", nullable=false)
	@NotNull
	private Usuario usuario;

	@Column(name="id_jogo", nullable=false)
	@NotNull
	private Long idJogo;

	@Column(name="nm_jogo", nullable=false)
	@NotEmpty
	private String nome;

	@Column(name="tp_inclusao", nullable=false)
	@NotNull
	private FormaInclusao formaInclusao;

	@Column(name="dt_inclusao", nullable=false)
	@NotNull
	private LocalDate dataInclusao;

}
