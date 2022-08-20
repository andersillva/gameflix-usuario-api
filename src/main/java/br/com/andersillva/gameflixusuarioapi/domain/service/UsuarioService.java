package br.com.andersillva.gameflixusuarioapi.domain.service;

import br.com.andersillva.gameflixusuarioapi.domain.model.Usuario;

public interface UsuarioService {

	public void registrar(Usuario usuario);

	public Usuario obterPorId(Long idUsuario);

}
