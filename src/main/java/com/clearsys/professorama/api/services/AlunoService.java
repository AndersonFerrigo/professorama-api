package com.clearsys.professorama.api.services;

import java.util.Optional;

import com.clearsys.professorama.api.entities.Aluno;

public interface AlunoService {
	
	
	

	/**
	 * Retorna um aluno pelo id
	 * 
	 * @param id
	 * @return Optional<Aluno> 
	 */
	
	Optional<Aluno> buscarPorId(Long id); 
	
	/**
	 * Retorna um Usuario de determinado aluno
	 * 
	 * @param usuario
	 * @return Optional<Aluno> 
	 */
		
	Optional<Aluno> buscarPorUsuario(String user); 
	
	
	/**
	 * Busca uma aluno cadastrado no sistema 
	 * 
	 * @param user
	 * @param senha
	 * @return Optional<Aluno>
	 * 
	 */
	//Aluno buscarLogin(String user, String senha);
	
	/**
	 * Retorna um RA de determinado aluno
	 * 
	 * @param ra
	 * @return Optional<Aluno> 
	 */
	
	Optional<Aluno> buscarPorSerie(String serie); 
	
	
	
	/**
	 * Cadastra um novo aluno na base de dados
	 * 
	 * @param aluno
	 * @return Aluno
	 *  
	 */
	Aluno persistir(Aluno aluno);

	/**
	 * Remove um aluno pelo id
	 * 
	 * @param id
	 */

	void remover(Long id);
}
