package com.clearsys.professorama.api.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.clearsys.professorama.api.entities.Lembretes;

@Transactional(readOnly = false)
@Repository
public interface LembreteRepository extends JpaRepository<Lembretes, Long> {

	@Override
	Optional<Lembretes> findById(Long id);

	List<Lembretes> findByMateria(String materia);

	Lembretes findByData(String data);

	List<Lembretes> findBySerie(String serie);

	@Modifying
	@Query(value = "delete from lembrete where id =:id ", nativeQuery = true)
	void deleteLembreteById(@Param("id") Long id);

}
