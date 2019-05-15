package com.clearsys.professorama.api.services.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clearsys.professorama.api.entities.Aluno;
import com.clearsys.professorama.api.repositories.AlunoRepository;
import com.clearsys.professorama.api.services.AlunoService;

@Service
public class AlunoServiceImpl implements AlunoService{
	
	private static final Logger log  = org.slf4j.LoggerFactory.getLogger(AlunoServiceImpl.class);
	
	@Autowired
	private AlunoRepository alunoRepository;


	@Override
	public Optional<Aluno> buscarPorUsuario(String user) {
		log.info("Buscando um aluno pelo usuario {}", user);
		return Optional.ofNullable(alunoRepository.findByUsuario(user)); 
	}

	@Override
	public Optional<Aluno> buscarPorRA(String senha) {
		log.info("Buscando um aluno pelo RA {}", senha);
		return Optional.ofNullable(alunoRepository.findBySenha(senha)); 

	}

	
	@Override
	public Aluno persistir(Aluno aluno) {
		log.info("Persistindo aluno {}", aluno);
		return this.alunoRepository.save(aluno);
	}

			
}
