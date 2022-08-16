package br.com.andersillva.gameflixusuarioapi.controller.form;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class AutenticacaoForm {

	@NotBlank
	private String email;

	@NotBlank
	private String senha;

}
