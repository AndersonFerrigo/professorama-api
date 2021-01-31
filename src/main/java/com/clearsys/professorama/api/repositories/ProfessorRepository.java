package com.clearsys.professorama.api.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.clearsys.professorama.api.entities.Professor;

@Transactional(readOnly = true)
public interface ProfessorRepository extends JpaRepository<Professor, Long> {
		
	Optional<Professor> findByUsuarioAndSenha(String usuario, String senha);

	Professor findByNome(String nome);

	List<Professor> findByMateria(String materia);

	Professor findByUsuario(String usuario);
	
	Professor findBySenha(String senha);

	Optional<Professor> findById(Long id);
	
	
}
