package br.com.andersillva.gameflixusuarioapi.controller.dto;

import java.time.LocalDate;

import br.com.andersillva.gameflixusuarioapi.domain.model.Usuario;
import lombok.Data;

@Data
public class UsuarioDTO {

	private Long id;

	private String nome;

	private String cpf;

	private LocalDate dataNascimento;

	private String email;

	public UsuarioDTO(Usuario usuario) {
		this.id = usuario.getId();
		this.nome = usuario.getNome();
		this.cpf = usuario.getCpf();
		this.dataNascimento = usuario.getDataNascimento();
		this.email = usuario.getEmail();
	}

}
