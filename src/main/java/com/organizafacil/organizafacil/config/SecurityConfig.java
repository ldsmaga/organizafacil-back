package com.organizafacil.organizafacil.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.organizafacil.organizafacil.security.JWTAuthenticationFilter;
import com.organizafacil.organizafacil.security.JWTAuthorizationFilter;
import com.organizafacil.organizafacil.security.JWTUtil;

@Configuration //classe de configuração
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	//declara rotas para liberar a autenticação:
	

	private static final String[] PUBLIC_MATCHERS_POST = {
			"/cadastro"
			
	};
	
//	private static final String[] PUBLIC_MATCHERS_GET = {
//			//insira codigo para liberar rota get
//	};
	
	
	//libera classes da autenticação jwt
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.cors().and().csrf().disable();//aplica o metodo cors definido abaixo, por padrão não são permitidas
		//requisições cross-origin
		http.authorizeRequests().antMatchers(HttpMethod.POST, PUBLIC_MATCHERS_POST).permitAll()//permita todas rotas no publicmatchers
		.anyRequest().authenticated(); //de resto, solicite autorização
		http.addFilter(new JWTAuthenticationFilter(authenticationManager(), jwtUtil));
		http.addFilter(new JWTAuthorizationFilter(authenticationManager(), jwtUtil, userDetailsService));
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); //assegura que o armazenamento seja stateless, para evitar ataques csrf
	}
	
	//configuração do CORS, libera acesso de multiplas fontes para vários caminhos.
	//permite o acesso aos endpoints por múltiplas fontes
	@Bean
	  CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues();
		configuration.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE", "OPTIONS"));
	    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
 		source.registerCorsConfiguration("/**", configuration);
	    return source;	
	  }
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
	}
	
	
	//declaro o BCryptPasswordEncoder para poder injetar em qualquer classe do sistema
	//para criptografar senhas
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
