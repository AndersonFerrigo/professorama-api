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
import com.clearsys.professorama.api.dtos.LembretesDto;
import com.clearsys.professorama.api.entities.Lembretes;
import com.clearsys.professorama.api.entities.Professor;
import com.clearsys.professorama.api.response.Response;
import com.clearsys.professorama.api.services.LembretesService;
import com.clearsys.professorama.api.services.ProfessorService;

@RestController
@RequestMapping("/api/lembrete")
@CrossOrigin("/*")
public class LembretesController {

	private static final Logger log = LoggerFactory.getLogger(LembretesController.class);
	Lembretes lembretes = new Lembretes();

	@Autowired
	private LembretesService lembretesService;

	@Autowired
	private ProfessorService professorService;

	public LembretesController() {	}

	//OK
	@PostMapping
	public ResponseEntity<Response<LembretesDto>> cadastrar(@Valid @RequestBody LembretesDto lembretesDto,
			BindingResult result) throws NoSuchAlgorithmException {

		log.info("Cadastrando Lembrete {}", lembretesDto.toString());
		Response<LembretesDto> response = new Response<LembretesDto>();
		Lembretes lembretes = this.converterDtoParaLembrete(lembretesDto, result);

		if (result.hasErrors()) {
			log.error("Erro validando dados de cadastro do Lembrete: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		lembretes = this.lembretesService.persistir(lembretes);
		response.setData(this.converterCadastroLembreteDto(lembretes));
		return ResponseEntity.ok(response);
	}
	
	//OK
	@GetMapping
	public List<Lembretes> findAll(){
		log.info("Buscando todos os lembretes.");
		return lembretesService.findAll();
		
	}
	
	//OK
	@GetMapping(value = "/{id}")
	public ResponseEntity<Lembretes> listarPorId(@PathVariable("id") long id) {
		log.info("Buscando lembretes pelo id {}", id);
		Optional<Lembretes> lembretes = this.lembretesService.findById(id);

		if (lembretes.toString().isEmpty()) {
			log.info("Lembretes não encontrado para o id {}", id);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		log.info("Buscando lembrete pelo id {} antes de chamar o metodo ", id);
		return new ResponseEntity<Lembretes>(lembretes.get(), HttpStatus.OK);
	}

	//OK
	@GetMapping(value = "/serie/{serie}")
	public ResponseEntity<List<Lembretes>> listarPorSerie(@PathVariable("serie") String serie) {
		log.info("Buscando atividades pela serie {}", serie);
		Optional<List<Lembretes>> lembrete = this.lembretesService.findBySerie(serie);

		if (!lembrete.isPresent()) {
			log.info("Lembrete não encontrado para a serie {}", serie);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		log.info("Buscando lembrete pela serie {} antes de chamar o metodo ", serie);
		return new ResponseEntity<List<Lembretes>>(lembrete.get(), HttpStatus.OK);
	}

	//OK	
	@GetMapping(value = "/materia/{materia}")
	public ResponseEntity<List<Lembretes>> listarPorMateria(@PathVariable("materia") String materia) {
		log.info("Buscando lembretes pela materia {}", materia);
		Optional<List<Lembretes>> lembrete = this.lembretesService.findByMateria(materia);

		if (!lembrete.isPresent()) {
			log.info("Lembrete não encontrado para a materia {}", materia);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		log.info("Buscando lembretes pela materia {} antes de chamar o metodo ", materia);
		return new ResponseEntity<List<Lembretes>>(lembrete.get(), HttpStatus.OK);
	}

	
	@PutMapping(value = "/atualizar/{id}")
	public ResponseEntity<Response<LembretesDto>> atualizar(@PathVariable("id") Long id,
			@Valid @RequestBody LembretesDto lembretesDto, BindingResult result)
			throws ParseException, NoSuchAlgorithmException {

		log.info("Atualizando lembrete {}", lembretesDto.toString());
		Response<LembretesDto> response = new Response<LembretesDto>();
		lembretesDto.setId(id);
		Lembretes lembretes = this.converterDtoParaLembrete(lembretesDto, result);

		if (result.hasErrors()) {
			log.error("Erro validando lembrete {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		lembretes = this.lembretesService.persistir(lembretes);
		response.setData(this.converterCadastroLembreteDto(lembretes));

		return ResponseEntity.ok(response);

	}

	@DeleteMapping(value = "/deletar/{id}")
	public ResponseEntity<Response<String>> remover(@PathVariable("id") Long id) {
		log.info("Removendo lembrete pelo id : {}", id);

		Response<String> response = new Response<String>();
		Optional<Lembretes> lembretes = this.lembretesService.findById(id);

		if (!lembretes.isPresent()) {
			log.error("Erro ao remover lembrete pelo id: {}", id);
			response.getErrors().add("Erro ao remover lembrete. Registro não encontrado para o id " + id);
			return ResponseEntity.badRequest().body(response);
		}

		this.lembretesService.deletar(id);
		return new ResponseEntity<>(HttpStatus.OK);

	}
	
	private LembretesDto converterCadastroLembreteDto(Lembretes lembretes) {

		LembretesDto lembretesDto = new LembretesDto();

		lembretesDto.setId(lembretes.getId());
		lembretesDto.setMateria(lembretes.getMateria());
		lembretesDto.setSerie(lembretes.getSerie());
		lembretesDto.setDataLembrete(lembretes.getDataLembrete());
		lembretesDto.setAssunto(lembretes.getAssunto());
		lembretesDto.setDescricao(lembretes.getDescricao());

		return lembretesDto;
	}
	
	private Lembretes converterDtoParaLembrete(LembretesDto lembretesDto, BindingResult result)
			throws NoSuchAlgorithmException {

		lembretes = new Lembretes();

		lembretes.setId(lembretesDto.getId());
		lembretes.setMateria(lembretesDto.getMateria());
		lembretes.setSerie(lembretesDto.getSerie());
		lembretes.setDataLembrete(lembretesDto.getDataLembrete());
		lembretes.setAssunto(lembretesDto.getAssunto());
		lembretes.setDescricao(lembretesDto.getDescricao());

		return lembretes;
	}

}
