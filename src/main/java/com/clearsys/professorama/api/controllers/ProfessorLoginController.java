package com.clearsys.professorama.api.controllers;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.clearsys.professorama.api.entities.Professor;
import com.clearsys.professorama.api.response.Response;
import com.clearsys.professorama.api.security.dto.JwtAuthenticationDto;
import com.clearsys.professorama.api.services.ProfessorService;
import com.clearsys.professorama.api.utils.PasswordUtils;


@RestController
@RequestMapping("/api/login-professor")
@CrossOrigin(origins ="*")

public class ProfessorLoginController {


	private static final  Logger LOG =LoggerFactory.getLogger(AlunoLoginController.class );
	
	@Autowired
	private ProfessorService professorService;
	
	
	public ProfessorLoginController() {
		// TODO Auto-generated constructor stub
	}
	
	@RequestMapping(value="/login/usuario/{usuario}/senha/{senha}" ,method=RequestMethod.GET )
	
	public ResponseEntity<Professor>logarProfessor (@PathVariable ("usuario") String usuario, 
			@PathVariable ("senha") String senha) throws NoSuchAlgorithmException{
			
		LOG.info("Buscando professor pelo usuario {} e pela senha {} ", usuario, senha);
		
		Optional<Professor> professor = this.professorService.buscalogin(usuario, senha);


		LOG.info("Validando dados login de Professor: {}", professor);
	
		if(!professor.isPresent()) {
			LOG.error("Erro validando dados cadastro do Professor: {}", professor);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);	
		}

		return ResponseEntity.ok(professor.get());
	}

}
