package com.clearsys.professorama.api.services.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clearsys.professorama.api.entities.Professor;
import com.clearsys.professorama.api.repositories.ProfessorRepository;
import com.clearsys.professorama.api.services.ProfessorService;

@Service
public class ProfessorServiceImpl implements ProfessorService {

	private static final Logger log = LoggerFactory.getLogger(ProfessorServiceImpl.class);

	@Autowired
	private ProfessorRepository professorRepository;

	@Override
	public Optional<Professor> buscarPorNome(String nome) {
		log.info("Buscando um professor pelo nome {} ", nome);
		return Optional.ofNullable(professorRepository.findByNome(nome));
	}

	@Override
	public Optional<Professor> buscarPorMateria(String materia) {
		log.info("Buscando um professor pela materia {} ", materia);
		return Optional.ofNullable(professorRepository.findByMateria(materia));
	}

	@Override
	public Optional<Professor> buscarPorUsuario(String usuario) {
		log.info("Buscando um professor pelo usuario {} ", usuario);
		return Optional.ofNullable(professorRepository.findByUsuario(usuario));
	}

	@Override
	public Optional<Professor> buscarPorId(Long id) {
		log.info("Buscando um professor pelo id {} ", id);
		return professorRepository.findById(id);
	}

	@Override
	public Optional<Professor> buscalogin(String usuario, String senha) {
		log.info("Logando professor pelo usuario {}  e senha {} ", usuario, senha);
		return professorRepository.findByUsuarioAndSenha(usuario, senha);
	}

}
