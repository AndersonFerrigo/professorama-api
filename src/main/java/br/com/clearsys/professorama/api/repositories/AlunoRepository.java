package br.com.clearsys.professorama.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.clearsys.professorama.api.entities.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long>{

		@Transactional(readOnly = true)
		Aluno findById(int id);
	
}
