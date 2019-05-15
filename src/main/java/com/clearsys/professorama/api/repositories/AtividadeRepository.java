package com.clearsys.professorama.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.clearsys.professorama.api.entities.Atividade;

@Transactional(readOnly = true)
public interface AtividadeRepository extends JpaRepository<Atividade, String>  {

		Atividade findByMateria(String materia);
		Atividade findByProfessor(String professor);
		Atividade findByDataEntrega(String dataEntrega);
		Atividade findByNivelEscolar(String nivelEscolar);
		
}
