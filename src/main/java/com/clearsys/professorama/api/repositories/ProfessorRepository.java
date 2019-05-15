package com.clearsys.professorama.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.clearsys.professorama.api.entities.Professor;

@Transactional(readOnly = true)
public interface ProfessorRepository extends JpaRepository<Professor, String>{
	
	Professor findByNome(String nome);
	Professor findByUsuario(String usuario);
	Professor findBySenha(String senha);
	
}
