package com.clearsys.professorama.api.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.clearsys.professorama.api.entities.Aluno;
import com.clearsys.professorama.api.entities.Professor;

public class JwtUserFactory {


	private JwtUserFactory() {
	}

	
	/**
	 * Converte e gera um JwtUser com base nos dados de um aluno.
	 * 
	 * @param aluno
	 * @return JwtUser
	 */
	public static JwtUser createAluno(Aluno aluno) {
		return new JwtUser(aluno.getId(), aluno.getUsuario(), aluno.getSenha(),
				mapToGrantedAuthorities(aluno.getPerfil()));
	}
	
	/**
	 * Converte e gera um JwtUser com base nos dados de um professor.
	 * 
	 * @param professor
	 * @return JwtUser
	 */
	
	public static JwtUser createProfessor(Professor professor) {
		return new JwtUser(professor.getId(), professor.getUsuario(),professor.getSenha(),
				mapToGrantedAuthorities(professor.getPerfil()));
	}

	/**
	 * Converte o perfil do usuário para o formato utilizado pelo Spring Security.
	 * 
	 * @param string
	 * @return List<GrantedAuthority>
	 */
	private static List<GrantedAuthority> mapToGrantedAuthorities(String string) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(string.toString()));
		return authorities;
	}

}
