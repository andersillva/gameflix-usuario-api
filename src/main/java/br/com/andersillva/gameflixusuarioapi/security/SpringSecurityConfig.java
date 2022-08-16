package br.com.andersillva.gameflixusuarioapi.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.andersillva.gameflixusuarioapi.controller.util.VersaoAPI;
import br.com.andersillva.gameflixusuarioapi.domain.repository.UsuarioRepository;

@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled=true, jsr250Enabled=true, prePostEnabled=true)
@Configuration
public class SpringSecurityConfig {

	@Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, TokenService tokenService, UsuarioRepository usuarioRepository) throws Exception {

        http.authorizeRequests()
    		.antMatchers(HttpMethod.POST, VersaoAPI.URI_BASE + "/*/login").permitAll()
    		.antMatchers(HttpMethod.POST, VersaoAPI.URI_BASE + "/*").permitAll()
    		.antMatchers(HttpMethod.GET, VersaoAPI.URI_BASE + "/*/hello").permitAll()
    		.anyRequest().authenticated().and().exceptionHandling().authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
    		.and().csrf().disable() //Usando JWT essa validação pode ser desabilitada
    		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    		.and().addFilterBefore(new TokenAuthenticationFilter(tokenService, usuarioRepository), UsernamePasswordAuthenticationFilter.class);

		http.headers().frameOptions().sameOrigin();

		return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().antMatchers(
	        		"/v3/api-docs/**",
	        		"/swagger-ui.html",
	        		"/swagger-ui/**"
        		);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
