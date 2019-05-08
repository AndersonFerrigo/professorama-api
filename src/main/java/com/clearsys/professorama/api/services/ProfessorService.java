package com.clearsys.professorama.api.services;

import java.util.Optional;

import com.clearsys.professorama.api.entities.Professor;

public interface ProfessorService {
	
	/**
	 * 
	 * @param id
	 * @return Optional<Professor>
	 */
	Optional<Professor> buscarPorId(int id);

	/**
	 * 
	 * @param nome
	 * @return Optional<Professor> 
	 */
	
	Optional<Professor> buscarPorNome(String nome);
	
	
}
