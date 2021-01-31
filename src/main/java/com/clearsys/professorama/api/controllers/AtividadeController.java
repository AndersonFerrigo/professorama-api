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

	public AtividadeController() {
	}

	//OK
	@PostMapping
	public ResponseEntity<Response<AtividadeDto>> cadastrar(@Valid @RequestBody AtividadeDto atividadeDto,
			BindingResult result) throws NoSuchAlgorithmException {

		log.info("Cadastrando Atividade {}", atividadeDto.toString());
		Response<AtividadeDto> response = new Response<AtividadeDto>();

		Atividade atividade = this.converterDtoParaAtividade(atividadeDto, result);

		if (result.hasErrors()) {
			log.error("Erro validando dados cadastro de Atividade: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		atividade = this.atividadeService.persistir(atividade);
		response.setData(this.converterCadastroAtividadeDto(atividade));

		return ResponseEntity.ok(response);
	}

	//OK
	@GetMapping
	public List<Atividade> findAll(){
		log.info("Buscando todas as atividades cadastradas.");
		return atividadeService.findAll();

	}
	
	//OK
	@GetMapping(value = "/serie/{serie}")
	public ResponseEntity<List<Atividade>> listarPorSerie(@PathVariable("serie") String serie) {
		log.info("Buscando atividades pela serie {}", serie);
		Optional<List<Atividade>> atividade = this.atividadeService.buscarPorSerie(serie);

		if (!atividade.isPresent()) {
			log.info("Atividade não encontrada para a serie {}", serie);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		log.info("Buscando atividades pela serie {} antes de chamar o metodo ", serie);
		return new ResponseEntity<List<Atividade>>(atividade.get(), HttpStatus.OK);
	}

	//OK
	@GetMapping(value = "/materia/{materia}")
	public ResponseEntity<List<Atividade>> listarPorMateria(@PathVariable("materia") String materia) {
		log.info("Buscando atividades pela materia {}", materia);

		Optional<List<Atividade>> atividade = this.atividadeService.buscarPorMateria(materia);

		if (!atividade.isPresent()) {
			log.info("Atividade não encontrada para a materia {}", materia);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		log.info("Buscando atividades pela maeria {} antes de chamar o metodo ", materia);
		return new ResponseEntity<List<Atividade>>(atividade.get(), HttpStatus.OK);
	}

	//OK
	@GetMapping(value = "/{id}")
	public ResponseEntity<Atividade> listarPorId(@PathVariable("id") Long id) {
		log.info("Buscando atividades pelo id {}", id);
		Optional<Atividade> atividade = this.atividadeService.buscarPorId(id);

		if (!atividade.isPresent()) {
			log.info("Atividade não encontrada para o id {}", id);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		log.info("Buscando atividades pelo id {} antes de chamar o metodo ", id);
		return new ResponseEntity<Atividade>(atividade.get(), HttpStatus.OK);
	}
	
	//OK
	@PutMapping(value = "/atualizar/{id}")
	public ResponseEntity<Response<AtividadeDto>> atualizar(@PathVariable("id") Long id,
			@Valid @RequestBody AtividadeDto atividadeDto, BindingResult result)
			throws ParseException, NoSuchAlgorithmException {

		log.info("Atualizando atividade {}", atividadeDto.toString());
		Response<AtividadeDto> response = new Response<AtividadeDto>();
		// validarProfessor(atividadeDto, result);
		atividadeDto.setId(id);
		Atividade atividade = this.converterDtoParaAtividade(atividadeDto, result);

		if (result.hasErrors()) {
			log.error("Erro validando atividade {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);

		}

		atividade = this.atividadeService.persistir(atividade);
		response.setData(this.converterCadastroAtividadeDto(atividade));

		return ResponseEntity.ok(response);

	}

	@DeleteMapping(value = "/deletar/{id}")
	public ResponseEntity<Response<String>> remover(@PathVariable("id") Long id) {
		log.info("Removendo atividade pelo id : {}", id);
		Response<String> response = new Response<String>();
		Optional<Atividade> atividade = this.atividadeService.buscarPorId(id);

		if (!atividade.isPresent()) {
			log.error("Erro ao remover atividade pelo id: {}", id);
			response.getErrors().add("Erro ao remover atividade. Registro não encontrado para o id " + id);
			return ResponseEntity.notFound().build();
		}

		this.atividadeService.deletar(id);
		return new ResponseEntity<>(HttpStatus.OK);

	}
	
	private AtividadeDto converterCadastroAtividadeDto(Atividade atividade) {

		AtividadeDto atividadeDto = new AtividadeDto();
		atividadeDto.setId(atividade.getId());
		atividadeDto.setMateria(atividade.getMateria());
		atividadeDto.setSerie(atividade.getSerie());
		atividadeDto.setNomeProfessor(atividade.getNomeProfessor());
		atividadeDto.setDataPostagem(atividade.getDataPostagem());
		atividadeDto.setDataEntrega(atividade.getDataEntrega());
		atividadeDto.setDescricao(atividade.getDescricao());

		return atividadeDto;
	}

	private Atividade converterDtoParaAtividade(AtividadeDto atividadeDto, BindingResult result)
			throws NoSuchAlgorithmException {

		atividade = new Atividade();

		atividade.setId(atividadeDto.getId());
		atividade.setMateria(atividadeDto.getMateria());
		atividade.setSerie(atividadeDto.getSerie());
		atividade.setNomeProfessor(atividadeDto.getNomeProfessor());
		atividade.setDataPostagem(atividadeDto.getDataPostagem());
		atividade.setDataEntrega(atividadeDto.getDataEntrega());
		atividade.setDescricao(atividadeDto.getDescricao());

		log.info("Atividade convertida dto sem id professor {}", atividade.toString());

		return atividade;

	}

}
