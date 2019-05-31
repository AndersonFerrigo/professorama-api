package com.clearsys.professorama.api.security.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.clearsys.professorama.api.entities.Aluno;
import com.clearsys.professorama.api.entities.Professor;
import com.clearsys.professorama.api.security.JwtUserFactory;
import com.clearsys.professorama.api.services.AlunoService;
import com.clearsys.professorama.api.services.ProfessorService;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private AlunoService alunoService;
	
	@Autowired
	private ProfessorService professorService;
	
	
	@Override
	public UserDetails loadUserByUsername(String user) throws UsernameNotFoundException {
		Optional<Aluno> aluno = alunoService.buscarPorUsuario(user);
		Optional<Professor> professor = professorService.buscarPorUsuario(user);
		
		if(aluno.isPresent()) {
			return JwtUserFactory.createAluno(aluno.get());
		}else if(professor.isPresent()) {
			return JwtUserFactory.createProfessor(professor.get());
		}
		
		throw new UsernameNotFoundException("Usuario ou senha errados");
		
	}
	
}
