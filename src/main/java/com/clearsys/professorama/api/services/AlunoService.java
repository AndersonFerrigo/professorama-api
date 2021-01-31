package com.clearsys.professorama.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.clearsys.professorama.api.entities.Aluno;

@Service
public interface AlunoService {

	List<Aluno> findAll();
	
	Optional<Aluno> findById(Long id);

	Optional<Aluno> findByUsuario(String user);

	Optional<Aluno> alunoLogin(String usuario, String senha);

	Optional<Aluno> findBySerie(String serie);

	Optional<Aluno> findByRa(String ra);

	Aluno persistir(Aluno aluno);

	void deletar(String ra);
}
