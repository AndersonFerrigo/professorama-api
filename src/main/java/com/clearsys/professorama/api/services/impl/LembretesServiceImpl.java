package com.clearsys.professorama.api.services.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.clearsys.professorama.api.entities.Atividade;
import com.clearsys.professorama.api.entities.Lembretes;
import com.clearsys.professorama.api.repositories.AtividadeRepository;
import com.clearsys.professorama.api.repositories.LembreteRepository;
import com.clearsys.professorama.api.services.LembretesService;

@Service
public class LembretesServiceImpl implements LembretesService{

	private static final Logger log = LoggerFactory.getLogger(AtividadeServiceImpl.class);

	@Autowired
	private LembreteRepository lembreteRepository ;

	
	@Override
	public Optional<List<Lembretes>> buscarPorMateria(String materia){
		log.info("Buscando um lembrete pela materia {}", materia);
		return Optional.ofNullable(lembreteRepository.findByMateria(materia));
	}
	
	@Override
	public Optional<Lembretes> buscarPorData(String data){
		log.info("Buscando um lembrete pela data {}", data);
		return Optional.ofNullable(lembreteRepository.findByData(data));
		
	}
	
	@Override
	public Optional<List<Lembretes>> buscarPorSerie(String serie){
		log.info("Buscando um lembrete pela serie {}", serie);
		return Optional.ofNullable(lembreteRepository.findBySerie(serie));
		
	}
	
	@Override
	@Cacheable("lembretePorId")
	public Optional<Lembretes> buscarPorId(Long id){
		log.info("Buscando um lembrete pelo id {}", id);
		return lembreteRepository.findById(id);
	}
	
	@Override
	@CachePut("lembretePorId")
	public Lembretes persistir(Lembretes lembretes) {
		log.info("Persistindo atividade {} ", lembretes);
		return this.lembreteRepository.save(lembretes);
	}

	@Override
	public void deletar (Long id) {
		log.info("Removendo atividade {} ", id);
		this.lembreteRepository.deleteLembreteById(id);
	}
	

}
