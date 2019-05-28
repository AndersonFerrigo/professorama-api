package com.clearsys.professorama.api.security.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.clearsys.professorama.api.entities.Aluno;
import com.clearsys.professorama.api.security.JwtUserFactory;
import com.clearsys.professorama.api.services.AlunoService;

public class JwtUserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private AlunoService alunoService;
	
	@Override
	public UserDetails loadUserByUsername(String user) throws UsernameNotFoundException {
		Optional<Aluno> aluno = alunoService.buscarPorUsuario(user);
		
		if(aluno.isPresent()) {
			return JwtUserFactory.create(aluno.get());
		}
		
		throw new UsernameNotFoundException("Usuario ou senha errados");
		
	}
	
}
