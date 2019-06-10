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

import com.clearsys.professorama.api.entities.Aluno;
import com.clearsys.professorama.api.response.Response;
import com.clearsys.professorama.api.security.dto.JwtAuthenticationDto;
import com.clearsys.professorama.api.services.AlunoService;
import com.clearsys.professorama.api.utils.PasswordUtils;

@RestController
@RequestMapping("/api/login-aluno")
@CrossOrigin(origins ="*")
public class AlunoLoginController {

	private static final  Logger LOG =LoggerFactory.getLogger(AlunoLoginController.class );
	
	@Autowired
	private AlunoService alunoService;
	
	public AlunoLoginController(){
	}
	
	@PostMapping
	public ResponseEntity<Response<JwtAuthenticationDto>> logarAluno (@Valid @RequestBody JwtAuthenticationDto authenticationDto, 
			BindingResult result )throws NoSuchAlgorithmException{
			
		LOG.info("Logando Aluno {}", authenticationDto.toString());
		Response<JwtAuthenticationDto> response = new Response<JwtAuthenticationDto>();
		
		Aluno aluno = this.converterDtoParaAluno(authenticationDto, result);
		
		if(result.hasErrors()) {
			LOG.error("Erro validando dados cadastro de Aluno: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
			
		}
	
		this.alunoService.buscarLogin(authenticationDto.getUsuario(),authenticationDto.getSenha());
		
		response.setData(this.converterCadastroAlunoDto(aluno));
	
		return ResponseEntity.ok(response);
	}


	private Aluno converterDtoParaAluno(JwtAuthenticationDto authenticationDto, BindingResult result)
			throws NoSuchAlgorithmException{
		
		Aluno aluno = new Aluno();
		aluno.setUsuario(authenticationDto.getUsuario());
		aluno.setSenha(PasswordUtils.gerarBCrypt(authenticationDto.getSenha()));
		return aluno;
	}
	
	private JwtAuthenticationDto converterCadastroAlunoDto(Aluno aluno) {
	
		JwtAuthenticationDto authenticationDto = new JwtAuthenticationDto();
		authenticationDto.setUsuario(aluno.getUsuario());
		authenticationDto.setSenha(aluno.getSenha());
		
		return authenticationDto;
	}


}
