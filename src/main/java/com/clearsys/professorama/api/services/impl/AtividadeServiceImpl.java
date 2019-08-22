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
import com.clearsys.professorama.api.repositories.AtividadeRepository;
import com.clearsys.professorama.api.services.AtividadeService;

@Service
public class AtividadeServiceImpl implements AtividadeService{

	private static final Logger log = LoggerFactory.getLogger(AtividadeServiceImpl.class);

	@Autowired
	private AtividadeRepository atividadeRepository;

	
	@Override
	public Optional<List<Atividade>> buscarPorMateria(String materia){
		log.info("Buscando uma atividade pela materia {}", materia);
		return Optional.ofNullable(atividadeRepository.findByMateria(materia));
	}
	
	@Override
	public Optional<Atividade> buscarPorDataEntrega(String dataEntrega){
		log.info("Buscando uma atividade pela data de entrega {}", dataEntrega);
		return Optional.ofNullable(atividadeRepository.findByDataEntrega(dataEntrega));
		
	}
	
	@Override
	public Optional<List<Atividade>> buscarPorSerie(String serie){
		log.info("Buscando uma atividade pela serie {}", serie);
		
		return Optional.ofNullable(atividadeRepository.findBySerie(serie));
		
	}
	
	@Override
	@Cacheable("atividadePorId")
	public Optional<Atividade> buscarPorId(Long id){
		log.info("Buscando uma atividade pelo id {}", id);
		return atividadeRepository.findById(id);
	}
	
	@Override
	@CachePut("atividadePorId")
	public Atividade persistir(Atividade atividade) {
		log.info("Persistindo atividade {} ", atividade);
		return this.atividadeRepository.save(atividade);
	}

	@Override
	public void deletar (Long id) {
		log.info("Removendo atividade {} ", id);
		this.atividadeRepository.deleteAtividadeById(id);
	}
	
	
}
