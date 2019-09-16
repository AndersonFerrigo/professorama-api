package com.clearsys.professorama.api.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.clearsys.professorama.api.entities.Atividade;

@Transactional(readOnly = false)

@Repository
public interface AtividadeRepository extends JpaRepository<Atividade, Long> {

	@Override
	Optional<Atividade> findById(Long id);

	List<Atividade> findByMateria(String materia);

	Atividade findByDataEntrega(String dataEntrega);

	List<Atividade> findBySerie(String serie);

	@Modifying
	@Query(value = "delete from atividade where id =:id ", nativeQuery = true)
	void deleteAtividadeById(@Param("id") Long id);

}
