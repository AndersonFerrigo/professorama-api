package com.clearsys.professorama.api.controllers;

import java.security.NoSuchAlgorithmException;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
	
	@PostMapping
	public ResponseEntity<Response<JwtAuthenticationDto>> logarProfessor (@Valid @RequestBody JwtAuthenticationDto authenticationDto, 
			BindingResult result )throws NoSuchAlgorithmException{
			
		LOG.info("Logando Professor {}", authenticationDto.toString());
		Response<JwtAuthenticationDto> response = new Response<JwtAuthenticationDto>();
		
		Professor professor = this.converterDtoParaProfessor(authenticationDto, result);
		
		if(result.hasErrors()) {
			LOG.error("Erro validando dados cadastro de Aluno: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
			
		}
	
		this.professorService.buscalogin(authenticationDto.getUsuario(), authenticationDto.getSenha());
		
		response.setData(this.converterCadastroProfessorDto(professor));
	
		return ResponseEntity.ok(response);
	}


	private Professor converterDtoParaProfessor(JwtAuthenticationDto authenticationDto, BindingResult result)
			throws NoSuchAlgorithmException{
		
		Professor professor = new Professor();
		professor.setUsuario(authenticationDto.getUsuario());
		professor.setSenha(PasswordUtils.gerarBCrypt(authenticationDto.getSenha()));
		return professor;
	}
	
	private JwtAuthenticationDto converterCadastroProfessorDto(Professor professor) {
	
		JwtAuthenticationDto authenticationDto = new JwtAuthenticationDto();
		authenticationDto.setUsuario(professor.getUsuario());
		authenticationDto.setSenha(professor.getSenha());
		
		return authenticationDto;
	}


}
