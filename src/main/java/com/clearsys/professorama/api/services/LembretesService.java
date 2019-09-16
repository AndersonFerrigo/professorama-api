package com.clearsys.professorama.api.services;

import java.util.List;
import java.util.Optional;

import com.clearsys.professorama.api.entities.Lembretes;

public interface LembretesService {

	/**
	 * Retorna uma atividade com base em um ID
	 * 
	 * @param id
	 * @return Optional<Atividade>
	 */
	Optional<Lembretes> buscarPorId(Long id);

	/**
	 * Retorna uma atividade com base na materia
	 * 
	 * @param materia
	 * @return Optional<Atividade>
	 */

	Optional<List<Lembretes>> buscarPorMateria(String materia);

	/**
	 * Retorna uma atividade com base na data de entrega
	 * 
	 * @param dataEntrega
	 * @return Optional<Atividade>
	 */
	Optional<Lembretes> buscarPorData(String data);

	/**
	 * Retorna um lembrete com base no nivel escolar
	 * 
	 * @param nivelEscolar
	 * @return Optional<Atividade>
	 */
	Optional<List<Lembretes>> buscarPorSerie(String serie);

	/**
	 * Cadastra um novo lembrete
	 * 
	 * @param lembretes
	 * @return Lembretes
	 */

	Lembretes persistir(Lembretes lembretes);

	/**
	 * Remove um lembrete da base de dados.
	 * 
	 * @param id
	 * @return
	 */
	void deletar(Long id);

}
