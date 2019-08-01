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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.clearsys.professorama.api.dtos.AtividadeDto;
import com.clearsys.professorama.api.entities.Atividade;
import com.clearsys.professorama.api.entities.Professor;
import com.clearsys.professorama.api.response.Response;
import com.clearsys.professorama.api.services.AtividadeService;
import com.clearsys.professorama.api.services.ProfessorService;

@RestController
@RequestMapping("/api/atividade")
@CrossOrigin("/*")
public class AtividadeController {

	private static final Logger log = LoggerFactory.getLogger(AtividadeController.class);
	Atividade atividade = new Atividade();
	
	@Autowired
	private AtividadeService atividadeService;
	
	@Autowired
	private ProfessorService professorService;
	
	public AtividadeController() {}
	
	/**
	 * Retorna uma atividade com base no id
	 * 
	 * @param id
	 * @return ResponseEntity<Response<CadastroAtividadeDto>>
	 */
	
	// Erro aqui retornando professor 
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Response<AtividadeDto>> listarPorId(@PathVariable("id") Long id){
		log.info("Buscando atividades pelo id {}", id);
		Response<AtividadeDto> response = new Response<AtividadeDto>();
		Optional<Atividade> atividade = this.atividadeService.buscarPorId(id);
	
		if(!atividade.isPresent()) {
			log.info("Atividade não encontrada para o id {}", id);
			response.getErrors().add("Atividade não encontrada para o id " + id);
			return ResponseEntity.badRequest().body(response);
		}
		
		log.info("Buscando atividades pelo id {} antes de chamar o metodo ", id);
		
		response.setData(this.converterCadastroAtividadeDto(atividade.get()));
		return ResponseEntity.ok(response);
	}
	
	// Criar um list para recuperar todas as atividades
	
	@RequestMapping(value="/buscarAtividade/{serie}" ,method=RequestMethod.GET )

	public ResponseEntity <List<Atividade>> listarPorSerie(@PathVariable("serie") String serie){
		log.info("Buscando atividades pela serie {}", serie);
		
		Optional<List<Atividade>> atividade = this.atividadeService.buscarPorSerie(serie);
	
		if(!atividade.isPresent()) {
			log.info("Atividade não encontrada para a serie {}", serie);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		log.info("Buscando atividades pela serie {} antes de chamar o metodo ", serie);
		
		return new ResponseEntity<List<Atividade>>(atividade.get(),HttpStatus.OK);

		
	}

	@RequestMapping(value="/buscarPorMateria/{materia}" ,method=RequestMethod.GET )

	public ResponseEntity <List<Atividade>> listarPorMateria(@PathVariable("materia") String materia){
		log.info("Buscando atividades pela materia {}", materia);
		
		Optional<List<Atividade>> atividade = this.atividadeService.buscarPorMateria(materia);
	
		if(!atividade.isPresent()) {
			log.info("Atividade não encontrada para a materia {}", materia);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		log.info("Buscando atividades pela maeria {} antes de chamar o metodo ", materia);
		
		return new ResponseEntity<List<Atividade>>(atividade.get(),HttpStatus.OK);

		
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
	public ResponseEntity<Response<AtividadeDto>> cadastrar (@Valid @RequestBody AtividadeDto atividadeDto, 
			BindingResult result )throws NoSuchAlgorithmException{
			
		log.info("Cadastrando Atividade {}", atividadeDto.toString());
		Response<AtividadeDto> response = new Response<AtividadeDto>();
		
		validarDadosExixtentes(atividadeDto, result );
		Atividade atividade = this.converterDtoParaAtividade(atividadeDto, result);
		
		if(result.hasErrors()) {
			log.error("Erro validando dados cadastro de Atividade: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
	
		atividade = this.atividadeService.persistir(atividade);
		response.setData(this.converterCadastroAtividadeDto(atividade));
		
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
	@PutMapping(value = "/{id}")
	public ResponseEntity<Response<AtividadeDto>> atualizar(@PathVariable("id") Long id,
				@Valid @RequestBody AtividadeDto atividadeDto, BindingResult result ) throws ParseException, NoSuchAlgorithmException{
		
		log.info("Atualizando atividade {}", atividadeDto.toString() );
		Response<AtividadeDto> response = new Response<AtividadeDto>();
//		validarProfessor(atividadeDto, result);
		atividadeDto.setId(id);
		Atividade atividade = this.converterDtoParaAtividade(atividadeDto, result);
		
		
			if(result.hasErrors()) {
				log.error("Erro validando atividade {}", result.getAllErrors());
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
				
			}
		
		atividade = this.atividadeService.persistir(atividade);
		response.setData(this.converterCadastroAtividadeDto(atividade));
		
		return ResponseEntity.ok(response);
		
		
	}
		
	@DeleteMapping(value= "/{id}")
	public ResponseEntity<Response<String>> remover(@PathVariable("id") Long id){
		log.info("Removendo atividade {}", id);
		
		Response<String> response = new Response<String>();
	
		Optional<Atividade> atividade = this.atividadeService.buscarPorId(id);
		
		if(!atividade.isPresent()) {
			log.error("Erro ao remover atividade {}", id);
			response.getErrors().add("Erro ao remover lançamento. Registro não encontrado para o id " + id);
			return ResponseEntity.badRequest().body(response);
		}
	
	this.atividadeService.remover(id);
	return ResponseEntity.ok(response);
	
	
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
	private void validarDadosExixtentes(AtividadeDto atividadeDto, BindingResult result) {
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
	private AtividadeDto converterCadastroAtividadeDto(Atividade atividade) {
				
			AtividadeDto atividadeDto = new AtividadeDto();
		
		atividadeDto.setId(atividade.getId());
		atividadeDto.setMateria(atividade.getMateria());
		atividadeDto.setSerie(atividade.getSerie());
		atividadeDto.setDataInicio(atividade.getDataInicio());
		atividadeDto.setDataEntrega(atividade.getDataEntrega());
		atividadeDto.setDescricao(atividade.getDescricao());
	
		return atividadeDto;
	}

	/**
	 * Converte os dados de DTO para Atividade 
	 * 
	 * @param atividadeDto
	 * @param result
	 * @return atividade
	 * @throws NoSuchAlgorithmException
	 */
	
	private Atividade converterDtoParaAtividade(AtividadeDto atividadeDto, BindingResult result)
			throws NoSuchAlgorithmException{
		
				
		atividade = new Atividade();
		
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
		atividade.setId(atividadeDto.getId());
		atividade.setMateria(atividadeDto.getMateria());
		atividade.setSerie(atividadeDto.getSerie());
		atividade.setDataInicio(atividadeDto.getDataInicio());
		atividade.setDataEntrega(atividadeDto.getDataEntrega());
		atividade.setDescricao(atividadeDto.getDescricao());
		
		log.info("Atividade convertida dto sem id professor {}",atividade.toString() );
			
		
		
		return atividade;
		
	}
	
	
	/*
	private Atividade converterDtoParaAtividade(CadastroAtividadeDto atividadeDto, BindingResult result)
			throws NoSuchAlgorithmException{
		
		Atividade atividade = new Atividade();
	
		atividade.setId(atividadeDto.getId());
		atividade.setMateria(atividadeDto.getMateria());
		atividade.setSerie(atividadeDto.getSerie());
		atividade.setDataInicio(atividadeDto.getDataInicio());
		atividade.setDataEntrega(atividadeDto.getDataEntrega());
		atividade.setDescricao(atividadeDto.getDescricao());
		atividade.getProfessor().getId();
		return atividade;
		
	}
	*/
	

}
