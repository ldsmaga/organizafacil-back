package com.organizafacil.organizafacil.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration //classe de configuração
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	//declara rotas para liberar a autenticação:
	private static final String[] PUBLIC_MATCHERS_GET = {
			"/notas",
			"/tarefas"
			
	};
	
	
	//libera classes da autenticação jwt
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.cors().and().csrf().disable();//aplica o metodo cors definido abaixo, por padrão não são permitidas
		//requisições cross-origin
		http.authorizeRequests()
		.antMatchers(HttpMethod.GET, PUBLIC_MATCHERS_GET).permitAll() //permita todas rotas no publicmatchers
		.anyRequest().authenticated(); //de resto, solicite autorização
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); //assegura que o armazenamento seja stateless, para evitar ataques csrf
	}
	
	//configuração do CORS, libera acesso de multiplas fontes para vários caminhos.
	//permite o acesso aos endpoints por múltiplas fontes
	@Bean
	  CorsConfigurationSource corsConfigurationSource() {
	    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
	    return source;	
	  }

}
