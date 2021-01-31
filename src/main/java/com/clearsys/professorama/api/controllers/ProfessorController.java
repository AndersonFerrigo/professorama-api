package com.clearsys.professorama.api.controllers;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.clearsys.professorama.api.entities.Professor;
import com.clearsys.professorama.api.response.Response;
import com.clearsys.professorama.api.services.ProfessorService;

@RestController
@RequestMapping("/api/professor")
@CrossOrigin(origins = "*")

public class ProfessorController {

	private static final Logger LOG = LoggerFactory.getLogger(ProfessorController.class);

	@Autowired
	private ProfessorService professorService;

	public ProfessorController() {	}

	//OK
	@GetMapping
	public List<Professor> listarProfessores(){
		LOG.info("Buscando todos os professores no sistema.");
		return professorService.findAll();
		
	}
	
	//OK
	@GetMapping(value = "/{id}")
	public ResponseEntity<Professor> buscaProfessor(@PathVariable long id){
		Optional<Professor> professor = professorService.findById(id);
		if(professor.isPresent()) {
			Professor professorModel = professor.get();
			return ResponseEntity.ok(professorModel);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	//OK
	@GetMapping(value = "/materia/{materia}")
	public List<Professor> findByMateria(@PathVariable String materia){
		LOG.info("Buscando todos os professores no sistema da matéria {}", materia);
		List<Professor> professoresMateria = professorService.findByMateria(materia);
		return professoresMateria;
	}
	
	//OK
	@GetMapping(value = "/usuario/{usuario}")
	public ResponseEntity<Professor> findByUser(@PathVariable String usuario){
		Optional<Professor> professorUser = professorService.findByUser(usuario);
		if(!professorUser.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(professorUser.get());
	}
	
	//OK
	@GetMapping(value = "/login/usuario/{usuario}/senha/{senha}")
	public ResponseEntity<Professor> logarProfessor(@PathVariable("usuario") String usuario,
			@PathVariable("senha") String senha) throws NoSuchAlgorithmException {

		LOG.info("Buscando professor pelo usuario {} e pela senha {} ", usuario, senha);
		Optional<Professor> professor = this.professorService.findLogin(usuario, senha);
		if (!professor.isPresent()) {
			LOG.error("Erro validando dados cadastro do Professor: {}", professor);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return ResponseEntity.ok(professor.get());
	}
	
	//OK
	@PostMapping
	public ResponseEntity<Response<Professor>> adicionar(@RequestBody Professor professor){
		LOG.info("Cadastrando Aluno {}", professor.toString());
		Response<Professor> response = new Response<Professor>();
		this.professorService.persistir(professor);
		return ResponseEntity.ok(response);

	}
	
	//OK
	@PutMapping(value = "/atualiza/{id}")
	public ResponseEntity<Response<Professor>> atualizar(@PathVariable("id") int id,
		@RequestBody Professor professor)throws ParseException, NoSuchAlgorithmException {

		LOG.info("Atualizando professor {}", professor.toString());
		Response<Professor> response = new Response<Professor>();
		professor.setId(id);
		professor = this.professorService.persistir(professor);
		return ResponseEntity.ok(response);

	}

	//OK
	@RequestMapping(value = "/deletar/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Response<String>> remover(@PathVariable("id") Long id) {
		LOG.info("Removendo professor pelo id : {}", id);
		Response<String> response = new Response<String>();

		Optional<Professor> professor = this.professorService.findById(id);

		if (!professor.isPresent()) {
			LOG.error("Erro ao remover professor pelo id: {}", id);
			response.getErrors().add("Erro ao remover professor. Registro não encontrado para o id " + id);
			return ResponseEntity.badRequest().body(response);
		}

		this.professorService.excluir(id);
		return new ResponseEntity<>(HttpStatus.OK);

	}
	
}
