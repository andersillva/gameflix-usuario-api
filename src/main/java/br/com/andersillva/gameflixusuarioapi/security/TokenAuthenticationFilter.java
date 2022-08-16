package br.com.andersillva.gameflixusuarioapi.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.andersillva.gameflixusuarioapi.domain.model.Usuario;
import br.com.andersillva.gameflixusuarioapi.domain.repository.UsuarioRepository;

public class TokenAuthenticationFilter extends OncePerRequestFilter {

	private final TokenService tokenService;
	private final UsuarioRepository repository;

	public TokenAuthenticationFilter(TokenService tokenService, UsuarioRepository repository) {
		this.tokenService = tokenService;
		this.repository = repository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

		String tokenFromHeader = getTokenFromHeader(request);
		boolean tokenValid = tokenService.isTokenValid(tokenFromHeader);
		if(tokenValid) {
			this.authenticate(tokenFromHeader);
		}

		filterChain.doFilter(request, response);
	}

	//Autentica o usuário a partir do token
	private void authenticate(String tokenFromHeader) {

		Long id = tokenService.getTokenId(tokenFromHeader);
		Optional<Usuario> optionalUser = repository.findById(id);

		if(optionalUser.isPresent()) {
			UsuarioAutenticado user = new UsuarioAutenticado(optionalUser.get());
			List<UsuarioAutenticado> lista = new ArrayList<>();
			lista.add(user);		
			UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user, null, lista);
			SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken); //Força a autenticação
		}
	}

	private String getTokenFromHeader(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if(token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
			return null;
		}

		return token.substring(7, token.length());
	}

}
