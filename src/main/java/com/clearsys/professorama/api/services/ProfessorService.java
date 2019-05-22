package com.clearsys.professorama.api.services;

import java.util.Optional;

import com.clearsys.professorama.api.entities.Professor;

public interface ProfessorService {

	/**
	 * Retorna um professor com base no id
	 * 
	 * @param id
	 * @return
	 */
	Optional<Professor> buscarPorId(Long id);
	
	/**
	 * Retorna um professor com base no nome
	 *  
	 * @param nome
	 * @return Optional<Professor> 
	 */
	
	Optional<Professor> buscarPorNome(String nome);
	
	/**
	 * Retorna um professor com base na materia
	 * 
	 * @param materia
	 * @return Optional<Professor>
	 */
	Optional<Professor> buscarPorMateria(String materia);
	
	/**
	 * Retorna um professor com base no usuario
	 * 
	 * @param usuario
	 * @return Optional<Professor>
	 */
	Optional<Professor> buscarPorUsuario(String usuario);
	
}
