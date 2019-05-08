package com.clearsys.professorama.api.services;

import java.util.Optional;

import com.clearsys.professorama.api.entities.Aluno;

public interface AlunoService {
	
	/**
	 * Retorna um id de determinado aluno
	 * 
	 * @param id
	 * @return Optional<Aluno> 
	 */
	Optional<Aluno> buscarPorId(int id); 
	
	/**
	 * Retorna um Usuario de determinado aluno
	 * 
	 * @param usuario
	 * @return Optional<Aluno> 
	 */
	
	Optional<Aluno> buscarPorUsuario(String user); 
	
	/**
	 * Retorna um RA de determinado aluno
	 * 
	 * @param ra
	 * @return Optional<Aluno> 
	 */
	
	Optional<Aluno> buscarPorRA(String ra); 
	
	
	
	/**
	 * Cadastra um novo aluno na base de dados
	 * 
	 * @param aluno
	 * @return Aluno
	 *  
	 */
	Aluno persistir(Aluno aluno);
}
