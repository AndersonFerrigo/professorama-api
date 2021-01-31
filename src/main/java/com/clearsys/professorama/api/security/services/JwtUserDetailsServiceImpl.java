package com.clearsys.professorama.api.security.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.clearsys.professorama.api.entities.Professor;
import com.clearsys.professorama.api.services.AlunoService;
import com.clearsys.professorama.api.services.ProfessorService;

@Service
public abstract class JwtUserDetailsServiceImpl implements UserDetailsService {

	public JwtUserDetailsServiceImpl() {
		super();
	}

	@Autowired
	private AlunoService alunoService;

	@Autowired
	private ProfessorService professorService;

	public UserDetails loadUserByUsername(String user, String senha) throws UsernameNotFoundException {
		// Aluno aluno = alunoService.buscarLogin(user, senha);
		Optional<Professor> professor = professorService.findByUser(user);
		/*
		 * if(aluno.isPresent()) { return JwtUserFactory.createAluno(aluno.get()); }else
		 * if(professor.isPresent()) { return
		 * JwtUserFactory.createProfessor(professor.get()); }
		 */
		throw new UsernameNotFoundException("Usuario ou senha errados");

	}

}
