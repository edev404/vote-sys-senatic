package com.senatic.votesys.security;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class DatabaseWebSecurity {
    
    @Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		
		http
				.authorizeHttpRequests()

				//Los recursos estáticos no requieren autenticación
				.requestMatchers("/images/**", "/css/**", "/images/**", "https://cdn**", "/webjars/**").permitAll()

				//Las vistas públicas no requieren autenticación
				.requestMatchers("/bcrypt/**")
				.permitAll()

				//Asignar permisos a URLs por ROLES
				.requestMatchers("/home/aprendiz/**", "/home/aprendiz/" , "/votos/**").hasAnyAuthority("APRENDIZ")
				.requestMatchers("/aprendices/**", "/candidatos/**", "/votaciones/**", "/home/administrador").hasAnyAuthority("ADMINISTRADOR")

				//Todas las demás URLs de la Aplicación requieren autenticación
				.anyRequest().authenticated()

				//El formulario de Login no requiere autenticacion
				.and()
				.formLogin()
							.loginPage("/login")
							.failureUrl("/login?error=true")
							.defaultSuccessUrl("/home", true)
							.permitAll()
				.and()
				.logout()
						.logoutUrl("/doLogout")
						.logoutSuccessUrl("/login")
						.permitAll();
		
		return http.build();
		 


		// http
		// 		.authorizeHttpRequests()
		// 		.anyRequest().permitAll();
		// return http.build();

	}

	@Bean UserDetailsManager users(DataSource dataSource) {
		JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource); 
		users.setUsersByUsernameQuery("select username, password, estatus from usuarios where username=?"); 
		users.setAuthoritiesByUsernameQuery(
		"select u.username, p.perfil from UsuarioPerfil up " +
		"inner join usuarios u on u.id = up.idUsuario " +
		"inner join perfiles p on p.id = up.idPerfil " + "where u.username = ?");
		return users; }

    @Bean PasswordEncoder passwordEncoder() { 
        return new BCryptPasswordEncoder();
    }
}
