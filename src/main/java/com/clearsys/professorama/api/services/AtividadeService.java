package com.clearsys.professorama.api.services;

import java.util.Optional;

import com.clearsys.professorama.api.entities.Atividade;

public interface AtividadeService {

	/**
	 * Retorna uma atividade com base em um ID
	 * 
	 * @param id
	 * @return Optional<Atividade>
	 */
	

	Optional<Atividade> buscarPorId(Long id);
	

	/**
	 * Retorna uma atividade com base na materia
	 * 
	 * @param materia
	 * @return Optional<Atividade>
	 */
	Optional<Atividade> buscarPorMateria(String materia);
	
	/**
	 * Retorna uma atividade com base na data de entrega
	 * 
	 * @param dataEntrega
	 * @return Optional<Atividade>
	 */
	Optional<Atividade> buscarPorDataEntrega(String dataEntrega);
	
	/**
	 * Retorna uma atividade com base no nivel escolar 
	 * 
	 * @param nivelEscolar
	 * @return Optional<Atividade>
	 */
	Optional<Atividade> buscarPorSerie(String serie);
	
	
	/**
	 * Cadastra uma nova atividade
	 * 
	 * @param atividade
	 * @return Atividade
	 */
	Atividade persistir(Atividade atividade);
	
	/**
	 * Remove uma atividade da base de dados.
	 * 
	 * @param id
	 */
	void remover (Long id);
}
