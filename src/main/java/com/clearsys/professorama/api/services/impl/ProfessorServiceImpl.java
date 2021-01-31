package com.clearsys.professorama.api.services.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.clearsys.professorama.api.entities.Professor;
import com.clearsys.professorama.api.repositories.ProfessorRepository;
import com.clearsys.professorama.api.services.ProfessorService;

@Service
public class ProfessorServiceImpl implements ProfessorService {

	private static final Logger log = LoggerFactory.getLogger(ProfessorServiceImpl.class);

	@Autowired
	private ProfessorRepository professorRepository;

	public List<Professor> findAll() {
		return professorRepository.findAll();
		
	}
	
	@Override
	public Optional<Professor> findByName(String nome) {
		log.info("Buscando um professor pelo nome {} ", nome);
		return Optional.ofNullable(professorRepository.findByNome(nome));
	}

	@Override
	public List<Professor> findByMateria(String materia) {
		log.info("Buscando um professor pela materia {} ", materia);
		List<Professor> materiaProfessor = professorRepository.findByMateria(materia);
		return materiaProfessor;
	}

	@Override
	public Optional<Professor> findByUser(String usuario) {
		log.info("Buscando um professor pelo usuario {} ", usuario);
		return Optional.ofNullable(professorRepository.findByUsuario(usuario));
	}

	@Override
	public Optional<Professor> findById(Long id) {
		log.info("Buscando um professor pelo id {} ", id);
		return professorRepository.findById(id);
	}

	@Override
	public Optional<Professor> findLogin(String usuario, String senha) {
		log.info("Logando professor pelo usuario {}  e senha {} ", usuario, senha);
		return professorRepository.findByUsuarioAndSenha(usuario, senha);
	}
	
	public Professor persistir(Professor professor) {
		log.info("Cadastrando novo professor");	
		return professorRepository.save(professor);
	}
	
	public void excluir(Long id) {
		log.info("Deletando professor atraves do id {}", id);
		professorRepository.deleteById(id);
	}
}
