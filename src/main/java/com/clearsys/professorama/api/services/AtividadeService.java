package com.clearsys.professorama.api.services;

import java.util.Optional;

import com.clearsys.professorama.api.entities.Atividade;

public interface AtividadeService {

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
	Optional<Atividade> buscarPorNivelEscolar(String nivelEscolar);
	
	/**
	 * Retorna uma atividade com base em um professor
	 * 
	 * @param professor
	 * @return Optional<Atividade>
	 */
	Optional<Atividade> buscarPorProfessor(String professor);
	
	/**
	 * Cadastra uma nova atividade
	 * 
	 * @param atividade
	 * @return Atividade
	 */
	Atividade persistir(Atividade atividade);
}
