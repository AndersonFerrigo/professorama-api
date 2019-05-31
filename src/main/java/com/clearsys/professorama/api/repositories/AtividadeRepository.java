package com.clearsys.professorama.api.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.clearsys.professorama.api.entities.Atividade;

@Transactional(readOnly = true)
public interface AtividadeRepository extends JpaRepository<Atividade, Long>  {
	
	@Override
	Optional<Atividade> findById(Long id);
	Atividade findByMateria(String materia);
	Atividade findByDataEntrega(String dataEntrega);
	Atividade findBySerie(String serie);
		
}
