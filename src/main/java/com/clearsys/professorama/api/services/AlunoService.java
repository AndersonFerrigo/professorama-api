package com.clearsys.professorama.api.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.clearsys.professorama.api.entities.Aluno;

@Service
public interface AlunoService {

	/**
	 * Retorna um aluno pelo id
	 * 
	 * @param id
	 * @return Optional<Aluno>
	 */

	Optional<Aluno> buscarPorId(int id);

	/**
	 * Retorna um Usuario de determinado aluno
	 * 
	 * @param usuario
	 * @return Optional<Aluno>
	 */

	Optional<Aluno> buscarPorUsuario(String user);

	/**
	 * Busca uma aluno cadastrado no sistema
	 * 
	 * @param usuario
	 *
	 * @return Optional<Aluno>
	 * 
	 */
	Optional<Aluno> buscarLogin(String usuario, String senha);

	/**
	 * Retorna um RA de determinado aluno
	 * 
	 * @param usuario
	 * @param senha
	 * @return Optional<Aluno>
	 */

	Optional<Aluno> buscarPorSerie(String serie);

	/**
	 * 
	 * @param ra
	 * @return
	 */
	Optional<Aluno> buscarPorRa(String ra);

	/**
	 * Cadastra um novo aluno na base de dados
	 * 
	 * @param aluno
	 * @return Aluno
	 * 
	 */
	Aluno persistir(Aluno aluno);

	/**
	 * Remove um aluno pelo id
	 * 
	 * @param id
	 */

	void deletar(String ra);
}
