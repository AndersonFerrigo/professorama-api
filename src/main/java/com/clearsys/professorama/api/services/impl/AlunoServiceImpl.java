package com.clearsys.professorama.api.services.impl;

import java.util.Optional;

import javax.persistence.PreRemove;

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
	public Optional<Aluno> buscarPorId(int id) {
		log.info("Buscando um aluno pelo id {}", id);
		return alunoRepository.findById(id); 

	}

	
	@Override
	public Optional<Aluno> buscarLogin(String usuario, String senha) {
		log.info("Buscando um aluno na implentação service pelo usuario {} e pela senha {} ", usuario,senha);
		return alunoRepository.findByUsuarioAndSenha(usuario, senha);
	}

	@Override
	public Optional<Aluno> buscarPorRa(String ra){
		log.info("Buscando um aluno na implentação service pelo ra {} ",ra);
		return alunoRepository.findByRa(ra);
	}
	
	@Override
	@CachePut("alunoPorId")
	public Aluno persistir(Aluno aluno) {
		log.info("Persistindo aluno {}", aluno);
		return this.alunoRepository.save(aluno);
	}

	@Override
	
	public void deletar(String ra) {
		log.info("Removendo aluno pelo ra: {} ", ra);
		this.alunoRepository.deleteAlunoByRa(ra);
		
	}
}
