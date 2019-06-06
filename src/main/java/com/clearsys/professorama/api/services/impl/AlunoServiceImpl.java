package com.clearsys.professorama.api.services.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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
	public Optional<Aluno> buscarPorSerie(String serie) {
		log.info("Buscando um aluno pela serie {}", serie);
		return Optional.ofNullable(alunoRepository.findBySerie(serie)); 

	}
	

	@Override
	@Cacheable("alunoPorId")
	public Optional<Aluno> buscarPorId(Long id) {
		log.info("Buscando um aluno pelo id {}", id);
		return alunoRepository.findById(id); 

	}

	
	@Override
	public Optional<Aluno> buscarLogin(String user, String senha) {
		log.info("Buscando um aluno pelo usuario {} e pela senha {} ", user,senha);
		return alunoRepository.findByUsuarioAndSenha(user, senha);
	}

	@Override
	@CachePut("alunoPorId")
	public Aluno persistir(Aluno aluno) {
		log.info("Persistindo aluno {}", aluno);
		return this.alunoRepository.save(aluno);
	}

	@Override
	public void remover(Long id) {
		log.info("Removendo aluno {} ", id);
		this.alunoRepository.deleteById(id);
		
	}
}
