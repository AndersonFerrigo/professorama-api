package com.clearsys.professorama.api.services;

import java.util.List;
import java.util.Optional;

import com.clearsys.professorama.api.entities.Professor;

public interface ProfessorService {

	List<Professor> findAll();

	Optional<Professor> findById(Long id);

	Optional<Professor> findLogin(String usuario, String senha);

	Optional<Professor> findByName(String nome);

	List<Professor> findByMateria(String materia);

	Optional<Professor> findByUser(String usuario);
	
	Professor persistir(Professor professor);
	
	void excluir(Long id);
}
