package com.clearsys.professorama.api.controllers;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.clearsys.professorama.api.dtos.AtividadeDto;
import com.clearsys.professorama.api.dtos.LembretesDto;
import com.clearsys.professorama.api.entities.Atividade;
import com.clearsys.professorama.api.entities.Lembretes;
import com.clearsys.professorama.api.entities.Professor;
import com.clearsys.professorama.api.response.Response;
import com.clearsys.professorama.api.services.AtividadeService;
import com.clearsys.professorama.api.services.LembretesService;
import com.clearsys.professorama.api.services.ProfessorService;

@RestController
@RequestMapping("/api/lembrete")
@CrossOrigin("/*")
public class LembretesController {

	private static final Logger log = LoggerFactory.getLogger(LembretesController.class);
	Lembretes lembretes  = new Lembretes();
	
	@Autowired
	private LembretesService lembretesService;
	
	@Autowired
	private ProfessorService professorService;
	
	public LembretesController(){}
	
	/**
	 * Retorna um lembrete com base no id
	 * 
	 * @param id
	 * @return ResponseEntzity<Response<CadastroAtividadeDto>>
	 */
	
	// Erro aqui retornando professor 
	
	@RequestMapping(value="/buscarLembrete/{id}" ,method=RequestMethod.GET )
	public ResponseEntity<Lembretes> listarPorId(@PathVariable("id") long id){
		log.info("Buscando lembretes pelo id {}", id);
		
		Optional<Lembretes> lembretes = this.lembretesService.buscarPorId(id);
	
		if(!lembretes.isPresent()) {
			log.info("Lembretes não encontrado para o id {}", id);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		
		log.info("Buscando lembrete pelo id {} antes de chamar o metodo ", id);
		return new ResponseEntity<Lembretes>(lembretes.get(),HttpStatus.OK);

	}
	
	// Criar um list para recuperar todas as atividades
	
	@RequestMapping(value="/buscarLembrete/{serie}" ,method=RequestMethod.GET )
	public ResponseEntity <List<Lembretes>> listarPorSerie(@PathVariable("serie") String serie){
		log.info("Buscando atividades pela serie {}", serie);
		
		Optional<List<Lembretes>> lembrete = this.lembretesService.buscarPorSerie(serie);
	
		if(!lembrete.isPresent()) {
			log.info("Lembrete não encontrado para a serie {}", serie);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		log.info("Buscando lembrete pela serie {} antes de chamar o metodo ", serie);
		return new ResponseEntity<List<Lembretes>>(lembrete.get(),HttpStatus.OK);
		
	}


	@RequestMapping(value="/buscarPorMateria/{materia}" ,method=RequestMethod.GET )
	public ResponseEntity <List<Lembretes>> listarPorMateria(@PathVariable("materia") String materia){
		log.info("Buscando lembretes pela materia {}", materia);
		
		Optional<List<Lembretes>> lembrete = this.lembretesService.buscarPorMateria(materia);
	
		if(!lembrete.isPresent()) {
			log.info("Lembrete não encontrado para a materia {}", materia);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		log.info("Buscando lembretes pela materia {} antes de chamar o metodo ", materia);
		return new ResponseEntity<List<Lembretes>>(lembrete.get(),HttpStatus.OK);
	}

	
	/**
	 * Cadastrando uma nova atividade
	 * 
	 * @param atividadeDto
	 * @param result
	 * @return 
	 * @throws NoSuchAlgorithmException
	 */
	
	
	@PostMapping
	public ResponseEntity<Response<LembretesDto>> cadastrar (@Valid @RequestBody LembretesDto lembretesDto, 
			BindingResult result )throws NoSuchAlgorithmException{
			
		log.info("Cadastrando Lembrete {}", lembretesDto.toString());
		Response<LembretesDto> response = new Response<LembretesDto>();
		
		validarDadosExixtentes(lembretesDto, result );
		Lembretes lembretes = this.converterDtoParaLembrete(lembretesDto, result);
		
		if(result.hasErrors()) {
			log.error("Erro validando dados de cadastro do Lembrete: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
	
		lembretes = this.lembretesService.persistir(lembretes);
		response.setData(this.converterCadastroLembreteDto(lembretes));
		
		return ResponseEntity.ok(response);
	}

	
	/**
	 * Atualiza os dados de uma atividade através do id.
	 * 
	 * @param id
	 * @param atividadeDto
	 * @param result
	 * @return ResponseEntity<Response<CadastroAtividadeDto>>
	 * @throws NoSuchAlgorithmException 
	 * @throws Exception
	 */
	
	@RequestMapping(value = "/atualizaLembrete/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Lembretes> atualizar(@PathVariable("id") long id,
				@Valid @RequestBody Lembretes lembretes, BindingResult result ) throws ParseException, NoSuchAlgorithmException{
		
	    log.info("Atualizando lembretes {}", lembretes.toString() );
		
		if(lembretes.equals(null)){
			log.error("Erro validando dados  Atividade: {}",lembretes );
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);	
		}
		
		lembretes = this.lembretesService.persistir(lembretes);
		return ResponseEntity.ok(lembretes);		
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Response<LembretesDto>> atualizar(@PathVariable("id") Long id,
				@Valid @RequestBody LembretesDto lembretesDto, BindingResult result ) throws ParseException, NoSuchAlgorithmException{
		
		log.info("Atualizando lembrete {}", lembretesDto.toString() );
		Response<LembretesDto> response = new Response<LembretesDto>();
//		validarProfessor(atividadeDto, result);
		lembretesDto.setId(id);
		Lembretes lembretes = this.converterDtoParaLembrete(lembretesDto, result);
		
			if(result.hasErrors()) {
				log.error("Erro validando lembrete {}", result.getAllErrors());
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
		
		lembretes = this.lembretesService.persistir(lembretes);
		response.setData(this.converterCadastroLembreteDto(lembretes));
		
		return ResponseEntity.ok(response);
		
		
	}
		
	@RequestMapping(value= "/deletarLembrete/{id}" , method = RequestMethod.DELETE)
	public ResponseEntity<Response<String>> remover(@PathVariable("id") Long id){
		log.info("Removendo lembrete pelo id : {}", id);
		
		Response<String> response = new Response<String>();
		Optional<Lembretes> lembretes = this.lembretesService.buscarPorId(id);
		
		if(!lembretes.isPresent()) {
			log.error("Erro ao remover lembrete pelo id: {}", id);
			response.getErrors().add("Erro ao remover lembrete. Registro não encontrado para o id " + id);
			return ResponseEntity.badRequest().body(response);
		}
		
		this.lembretesService.deletar(id);
		return new ResponseEntity<>(HttpStatus.OK);	
		
}
	
	private void validarProfessor(@Valid AtividadeDto atividadeDto, BindingResult result) {
		if (atividadeDto.getProfessor_id() == null) {
			result.addError(new ObjectError("professor", "professor não informado."));
			return;
		}

		log.info("Validando professor id {}: ", atividadeDto.getProfessor_id());
		
		Optional<Professor> professor = this.professorService.buscarPorId(atividadeDto.getProfessor_id());
		
		if (!professor.isPresent()) {
			result.addError(new ObjectError("professor", "Professor não encontrado. ID inexistente."));
		}
		
	}

	/**
	 * Verifica se o Aluno já existe no banco de dados
	 * 
	 * @param alunoDto
	 * @param result
	 */
	private void validarDadosExixtentes(LembretesDto lembretesDto, BindingResult result) {
		/*
		this.atividadeService.buscarPorDataEntrega(atividadeDto.getDataEntrega())
				.ifPresent(ativ->result.addError(new ObjectError("Atividade", "Data de entrega ja existente")));
		this.atividadeService.buscarPorMateria(atividadeDto.getMateria())
				.ifPresent(ativ->result.addError(new ObjectError("Atividade", "Materia ja cadastrada")));
		this.atividadeService.buscarPorNivelEscolar(atividadeDto.getNivelEscolar())
				.ifPresent(ativ->result.addError(new ObjectError("Atividade", "Nivel Escolar ja cadastrado")));
		*/
	}

	/**
	 * 
	 * @param atividade
	 * @return atividadeDto
	 */
	private LembretesDto converterCadastroLembreteDto(Lembretes lembretes) {
		
		LembretesDto lembretesDto = new LembretesDto();
		
		lembretesDto.setId(lembretes.getId());
		lembretesDto.setMateria(lembretes.getMateria());
		lembretesDto.setSerie(lembretes.getSerie());
		lembretesDto.setData(lembretes.getData());
		lembretesDto.setAssunto(lembretes.getAssunto());
		lembretesDto.setDescricao(lembretes.getDescricao());
	
		return lembretesDto;
	}

	/**
	 * Converte os dados de DTO para Lembrete 
	 * 
	 * @param lembreteDto
	 * @param result
	 * @return lembretes
	 * @throws NoSuchAlgorithmException
	 */
	
	private Lembretes converterDtoParaLembrete(LembretesDto lembretesDto, BindingResult result)
			throws NoSuchAlgorithmException{

		lembretes = new Lembretes();
		
		/*	
		if(!atividadeDto.getId().equals(null)) {
			Optional<Atividade> ativ = this.atividadeService.buscarPorId(atividadeDto.getId());
			if(ativ.isPresent()) {
				atividade = ativ.get();
			}else {
				result.addError(new ObjectError("atividade", "Atividade não encontrada."));

			}
		}else {
			atividade.setProfessor(new Professor());
			atividade.getProfessor().setId(atividadeDto.getProfessor_id());
		}
		
		*/
		lembretes.setId(lembretesDto.getId());
		lembretes.setMateria(lembretesDto.getMateria());
		lembretes.setSerie(lembretesDto.getSerie());
		lembretes.setData(lembretesDto.getData());
		lembretes.setAssunto(lembretesDto.getAssunto());
		lembretes.setDescricao(lembretesDto.getDescricao());
		
		log.info("Atividade convertida dto sem id professor {}",lembretes.toString() );
		
		return lembretes;
	}
			
}

