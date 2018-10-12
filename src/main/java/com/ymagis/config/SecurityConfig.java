package com.ymagis.config;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Configuration;

import org.springframework.http.HttpMethod;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration

@EnableWebSecurity

public class SecurityConfig extends WebSecurityConfigurerAdapter{

	

	@Autowired

	private UserDetailsService userDetailsService;

	@Autowired

	private BCryptPasswordEncoder bCryptPasswordEncoder;

	

	

	

	

	@Override

	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

       auth.userDetailsService(userDetailsService)

       .passwordEncoder(bCryptPasswordEncoder);



	}

	

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		//remplacer le system d'authentification par reference par le system d'authentification par valeur
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//           http.formLogin();
        http.authorizeRequests().antMatchers("/","/index.html","/js/**","/fonts/**","/views/**", "/registerUser", "/login", "/favicon.ico","/css/**","/node_modules/**",
        		"/mois/**","/nbrEmprun/**","/nvClient","/emp","/nvEmp","/nvMat","/etatMat","/img/**").permitAll();
        //gerer les client
        http.authorizeRequests().antMatchers(HttpMethod.POST,"/clients/**").hasAuthority("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.DELETE,"/clients/**").hasAuthority("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.PUT,"/clients/**").hasAuthority("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/listClients/**").hasAuthority("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/clients/**").hasAuthority("ADMIN");
     //gerer les emprunts
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/chercherClients/**").hasAuthority("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/chercherMateriels/**").hasAuthority("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/materiel/**").hasAuthority("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.POST,"/clients/**").hasAuthority("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.PUT,"/materiel/**").hasAuthority("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.PUT,"/materiels/**").hasAuthority("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.PUT,"/client/**").hasAuthority("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/client/**").hasAuthority("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/empruntRetard/**").hasAuthority("ADMIN");
   //gerer les materiels
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/materiels/**").hasAuthority("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/categories/**").hasAuthority("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/materielss/**").hasAuthority("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/cherchermateriels/**").hasAuthority("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.POST,"/materiel/**").hasAuthority("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.DELETE,"/materiel/**").hasAuthority("ADMIN");
        http.authorizeRequests().anyRequest().authenticated();
        http.httpBasic().disable();
        http.addFilter(new JWTAuthenticationFilter(authenticationManager()));
        http.addFilterBefore(new JWTAutorisationFilter(),UsernamePasswordAuthenticationFilter.class);
           
		
	}


}

