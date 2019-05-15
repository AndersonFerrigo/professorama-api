package com.clearsys.professorama.api.services.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
	public Optional<Atividade> buscarPorMateria(String materia){
		log.info("Buscando uma atividade pela materia {}", materia);
		return Optional.ofNullable(atividadeRepository.findByMateria(materia));
	}
	
	@Override
	public Optional<Atividade> buscarPorDataEntrega(String dataEntrega){
		log.info("Buscando uma atividade pela data de entrega {}", dataEntrega);
		return Optional.ofNullable(atividadeRepository.findByDataEntrega(dataEntrega));
		
	}
	
	@Override
	public Optional<Atividade> buscarPorNivelEscolar(String nivelEscolar){
		log.info("Buscando uma atividade pelo nivel escolar {}", nivelEscolar);
		return Optional.ofNullable(atividadeRepository.findByNivelEscolar(nivelEscolar));
		
	}
	
	@Override
	public Optional<Atividade> buscarPorProfessor(String professor){
		log.info("Buscando uma atividade pelo professor {}", professor);
		return Optional.ofNullable(atividadeRepository.findByProfessor(professor));
	}
	
	@Override
	public Atividade persistir(Atividade atividade) {
		log.info("Persistindo atividade {} ", atividade);
		return this.atividadeRepository.save(atividade);
		
	}
	
}
