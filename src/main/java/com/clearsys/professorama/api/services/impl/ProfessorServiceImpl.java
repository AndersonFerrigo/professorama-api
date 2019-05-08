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
	public Optional<Professor> buscarPorId(int id) {
		log.info("Buscando um professor através di id {}",id);
		return Optional.ofNullable(professorRepository.findById(id));
	}

	@Override
	public Optional<Professor> buscarPorNome(String nome) {
		log.info("Buscando um professor pelo nome {} ", nome);
		return Optional.ofNullable(professorRepository.findByNome(nome));
	}
	
}
