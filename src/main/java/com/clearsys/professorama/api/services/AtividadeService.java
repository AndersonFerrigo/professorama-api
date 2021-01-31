package com.clearsys.professorama.api.services;

import java.util.List;
import java.util.Optional;

import com.clearsys.professorama.api.entities.Atividade;

public interface AtividadeService {
	
	List<Atividade> findAll();
	
	Optional<Atividade> buscarPorId(Long id);

	Optional<List<Atividade>> buscarPorMateria(String materia);

	Optional<Atividade> buscarPorDataEntrega(String dataEntrega);

	Optional<List<Atividade>> buscarPorSerie(String serie);

	Atividade persistir(Atividade atividade);

	void deletar(Long id);
}
