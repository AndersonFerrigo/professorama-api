package com.clearsys.professorama.api.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.clearsys.professorama.api.entities.Aluno;
import com.clearsys.professorama.api.entities.Professor;
import com.clearsys.professorama.api.enums.PerfilEnum;

public class JwtUserFactory {


	private JwtUserFactory() {
	}

	
	/**
	 * Converte e gera um JwtUser com base nos dados de um aluno.
	 * 
	 * @param funcionario
	 * @return JwtUser
	 */
	public static JwtUser createAluno(Aluno aluno) {
		return new JwtUser(aluno.getId(), aluno.getUsuario(), aluno.getSenha(),
				mapToGrantedAuthorities(aluno.getPerfil()));
	}
	
	
	public static JwtUser createProfessor(Professor professor) {
		return new JwtUser(professor.getId(), professor.getUsuario(),professor.getSenha(),
				mapToGrantedAuthorities(professor.getPerfil()));
	}

	/**
	 * Converte o perfil do usuário para o formato utilizado pelo Spring Security.
	 * 
	 * @param perfilEnum
	 * @return List<GrantedAuthority>
	 */
	private static List<GrantedAuthority> mapToGrantedAuthorities(PerfilEnum perfilEnum) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(perfilEnum.toString()));
		return authorities;
	}

}
