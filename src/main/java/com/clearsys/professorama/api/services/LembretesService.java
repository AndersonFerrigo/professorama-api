package com.clearsys.professorama.api.services;

import java.util.List;
import java.util.Optional;

import com.clearsys.professorama.api.entities.Lembretes;

public interface LembretesService {

	Optional<Lembretes> findById(Long id);
	
	List<Lembretes> findAll();

	Optional<List<Lembretes>> findByMateria(String materia);

	Optional<Lembretes> findByData(String data);

	Optional<List<Lembretes>> findBySerie(String serie);
	
	Optional<List<Lembretes>> findByAssunto(String assunto);
	
	Lembretes persistir(Lembretes lembretes);

	void deletar(Long id);

}
