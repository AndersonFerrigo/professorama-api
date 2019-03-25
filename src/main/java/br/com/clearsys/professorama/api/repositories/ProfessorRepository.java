package br.com.clearsys.professorama.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.clearsys.professorama.api.entities.Professor;


public interface ProfessorRepository extends JpaRepository<Professor, Long>{
	
	Professor findById(int id);
//	Professor findByName(String nome);
}
